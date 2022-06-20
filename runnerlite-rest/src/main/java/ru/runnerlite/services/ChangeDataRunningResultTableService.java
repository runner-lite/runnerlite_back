package ru.runnerlite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.RunningResult;
import ru.runnerlite.entities.SecUser;
import ru.runnerlite.entities.TeamsRunningCount;
import ru.runnerlite.entities.dto.*;
import ru.runnerlite.repositories.*;
import ru.runnerlite.services.interfaces.IChangeDataRunningResultTableService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

@Service
public class ChangeDataRunningResultTableService implements IChangeDataRunningResultTableService {

    private TeamsRunningCountRepository teamsRunningCountRepository;
    private TeamRepository teamRepository;
    private SecUserRepository secUserRepository;
    private RunningResultRepository runningResultRepository;
    private RunnerCountRepository runnerCountRepository;

    @Autowired
    public ChangeDataRunningResultTableService(TeamsRunningCountRepository teamsRunningCountRepository, TeamRepository teamRepository, SecUserRepository secUserRepository, RunningResultRepository runningResultRepository, RunnerCountRepository runnerCountRepository) {
        this.teamsRunningCountRepository = teamsRunningCountRepository;
        this.teamRepository = teamRepository;
        this.secUserRepository = secUserRepository;
        this.runningResultRepository = runningResultRepository;
        this.runnerCountRepository = runnerCountRepository;
    }

    //поиск забега для внесения изменений в таблицу результатов
    @Override
    public TeamsRunningCountDto findTeamsRunningCountByRunningDate(String currentUserName) {
        Integer teamId = teamRepository.findMyTeam(currentUserName).getId();
        Optional<TeamsRunningCountDto> teamsRunningCountDto = teamsRunningCountRepository.findTeamsRunningCountByRunningDate(getCurrentDate(), teamId);
        if (!teamsRunningCountDto.isPresent()) {
            System.out.println("Данные отсутствуют");
            return null;
        }
        return teamsRunningCountDto.get();
    }

    //получение списка внесенных результатов забега
    @Override
    public List<ChangeTableDto> findListRunningResult(String currentUserName) {
        List<ChangeTableDto> listRunners = new ArrayList<>();
        try {
            Integer teamsRunningCountId = findTeamsRunningCountByRunningDate(currentUserName).getId();
            List<RunningResultForChangeTableDto> listRunningResult = runningResultRepository.findListRunningResult(teamsRunningCountId);
            for (int i = 0; i < listRunningResult.size(); i++) {
                ChangeTableDto changeTableDto = new ChangeTableDto();
                changeTableDto.setFinishPlace(listRunningResult.get(i).getFinishPlace());
                changeTableDto.setUserId(listRunningResult.get(i).getSecUserId());
                changeTableDto.setUserName(listRunningResult.get(i).getUseNick() ? listRunningResult.get(i).getNick() : listRunningResult.get(i).getFullNameUser());
                changeTableDto.setResultString(calculateInMinute(listRunningResult.get(i).getResult()));
                listRunners.add(i, changeTableDto);
            }
        }
        catch (Exception n) {
            System.out.println("Данные отсутствуют");
            return null;
        }
        return listRunners;
    }

    //получение списка пользователей записавшихся на забег, но по которым еще не внесен результат
    @Override
    public List<UserNameDto> findAllRunnerId(String currentUserName) {
        List<UserNameDto> listRunners = new ArrayList<>();
        try {
            Integer teamsRunningCountId = findTeamsRunningCountByRunningDate(currentUserName).getId();
            List<Integer> allRunnerId = runnerCountRepository.findAllRunnerId(teamsRunningCountId);
            List<ChangeTableDto> changeTableDtoList = findListRunningResult(currentUserName);
            for (int i = 0; i < changeTableDtoList.size(); i++) {
                if (allRunnerId.contains(changeTableDtoList.get(i).getUserId())) {
                    allRunnerId.remove(changeTableDtoList.get(i).getUserId());
                }
            }
            for (int i = 0; i < allRunnerId.size(); i++) {
                RunningResultForChangeTableDto runningResultForChangeTableDto = runnerCountRepository.findRunnerById(allRunnerId.get(i), teamsRunningCountId);
                UserNameDto userNameDto = new UserNameDto();
                userNameDto.setUserId(runningResultForChangeTableDto.getSecUserId());
                userNameDto.setName(runningResultForChangeTableDto.getUseNick() ? runningResultForChangeTableDto.getNick() : runningResultForChangeTableDto.getFullNameUser());
                listRunners.add(userNameDto);
            }
        }
        catch (Exception n){
            System.out.println("Данные отсутствуют");
            return null;
        }
        return listRunners;
    }

    //внесение результатов забега по определенному бегуну
    @Override
    public boolean insertRunnerResult(String currentUserName, Integer userId, String result) throws ParseException {
        Integer teamsRunningCountId = findTeamsRunningCountByRunningDate(currentUserName).getId();
        Optional<SecUser> user = secUserRepository.findSecUserById(userId);
        TeamsRunningCount teamsRunningCount = teamsRunningCountRepository.getById(teamsRunningCountId);
        Optional<RunningResult> runningResult = runningResultRepository.findRunningResult(user.get().getId(), teamsRunningCountId);
        if (!runningResult.isPresent()) {
            runningResultRepository.save(new RunningResult(null,
                    user.get(),
                    parseDateInSecond(result),
                    teamsRunningCount,
                    null));
        } else
            runningResultRepository.changeRunningResult(user.get().getId(), teamsRunningCountId, user.get(), parseDateInSecond(result), teamsRunningCount);
        return true;
    }

    //получение текущей даты для поиска сегодняшнего забега для внесения результатов
    public Instant getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 03:00:00");
        String str = sdf.format(date);
        Timestamp param = new Timestamp(date.getTime());
        Instant dateFrom = Instant.ofEpochMilli(Timestamp.valueOf(str).getTime());
        return dateFrom;
    }

    //парсинг времени результата забега в секунды
    public Integer parseDateInSecond(String result) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date parseDate = sdf.parse(result);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate);
        Integer second = calendar.get(Calendar.HOUR) * 3600 + calendar.get(Calendar.MINUTE) * 60 + calendar.get(Calendar.SECOND);
        return second;
    }

    //преобразование секунд в минуты и часы
    public String calculateInMinute(Integer seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, seconds);
       return new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
    }

//    @Override
//    public void changeTeamsRunningCountStatus(String currentUserName, Integer runningNumber) {
//        Integer teamId = teamRepository.findMyTeam(currentUserName).getId();
//        teamsRunningCountRepository.changeTeamsRunningCountStatus(teamId, runningNumber);
//    }
}
