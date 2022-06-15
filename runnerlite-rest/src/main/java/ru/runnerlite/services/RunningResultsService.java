package ru.runnerlite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.RunningResult;
import ru.runnerlite.entities.TeamsRunningCount;
import ru.runnerlite.entities.dto.RunningPlaningDto;
import ru.runnerlite.entities.dto.RunningResultDto;
import ru.runnerlite.entities.dto.TeamsRunningCountDto;
import ru.runnerlite.repositories.RunnerCountRepository;
import ru.runnerlite.repositories.RunningResultRepository;
import ru.runnerlite.repositories.SecUserRepository;
import ru.runnerlite.repositories.TeamsRunningCountRepository;
import ru.runnerlite.repositories.VolunteerRepository;
import ru.runnerlite.services.interfaces.IRunningResultsService;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RunningResultsService implements IRunningResultsService {

    private final RunningResultRepository runningResultRepository;
    private final SecUserService userService;
    private final TeamsRunningCountRepository runningRepository;
    private final SecUserRepository secUserRepository;
    private final RunnerCountRepository runnerCountRepository;
    private final VolunteerRepository volunteerRepository;

    private final LetterService letterService;

    @Autowired
    public RunningResultsService(RunningResultRepository runningResultRepository,
                                 SecUserService userService,
                                 TeamsRunningCountRepository runningRepository,
                                 SecUserRepository secUserRepository,
                                 RunnerCountRepository runnerCountRepository,
                                 VolunteerRepository volunteerRepository,
                                 LetterService letterService) {
        this.runningResultRepository = runningResultRepository;
        this.userService = userService;
        this.runningRepository = runningRepository;
        this.secUserRepository = secUserRepository;
        this.runnerCountRepository = runnerCountRepository;
        this.volunteerRepository = volunteerRepository;
        this.letterService = letterService;
    }

    @Override
    public List<RunningResultDto> findAllResult() {
        List<RunningResultDto> runningResult = runningResultRepository.findAllResult();
        return runningResult;
    }
    
    @Override
    public RunningResultDto getLastRunningResult(String currentUserName) {
        List<RunningResultDto> runningResult = runningResultRepository.findLastResult(currentUserName, PageRequest.of(0,1));
        if (runningResult.size() == 0) {
            return null;
        }
        return runningResult.get(0);
    }

    @Override
    public Integer historicalVolunteerismCount(String currentUserName) {
        return volunteerRepository.historicalVolunteerismCount(currentUserName);
    }

    @Override
    public Integer historicalRunnerCount(String currentUserName) {
        return runnerCountRepository.historicalRunnerCount(currentUserName);
    }

    @Override
    public List<TeamsRunningCountDto> getTeamRunningResults(Integer teamId) {
        List<TeamsRunningCountDto> outList = runningRepository.getTeamRunningResults(teamId);
        if(outList.isEmpty()){
            throw new IllegalArgumentException("История забегов команды с id = " + teamId + " не найдена.");
        }
        return outList;
    }

    @Override
    public List<RunningPlaningDto> getTeamRunningStatisticWithLimit(Integer teamId, Integer counter) {
        List<TeamsRunningCountDto> teamsRunningCountDtoList = runningRepository.getTeamRunningResults(teamId,PageRequest.of(0,counter));
        if(teamsRunningCountDtoList.isEmpty()){
            throw new IllegalArgumentException("История забегов команды с id = " + teamId + " не найдена.");
        }
        return teamsRunningCountDtoList.stream().map(teamsRunningCountDto -> new RunningPlaningDto(teamsRunningCountDto,
                runnerCountRepository.countRunners(teamsRunningCountDto.getId()),
                volunteerRepository.countVolunteers(teamsRunningCountDto.getId()))).collect(Collectors.toList());
    }

    @Override
    public List<RunningPlaningDto> getTeamRunningStatistic(Integer teamId) {
        return getTeamRunningStatisticWithLimit(teamId,5); // поставил что по умолчанию будет возвращать 5 записей
    }

    //Метод меняет статус забега.
    @Override
    @Transactional
    public void changeStatusTeamRunningStatistic(Integer teamId, Integer runningId, String newStatus) {
        TeamsRunningCount runningCount= runningRepository.findTeamsRunningCountByIdAndTeamId(teamId,runningId);
        if(newStatus.equals("Выполнен")){
            calcRunningResultPlaces(runningCount.getId()); //расставляем места
            runnerCountRepository.getRunnersByTeamRunningCountId(runningCount.getId()).forEach(runnerCount -> runnerCount.setStatus(1)); //Обновляем статус бегунов
            letterService.sendRunningResultsToRunner(runningCount.getNumber(),runningCount.getTeams().getId()); //рассылам участникам забега письма с результатом
        }
        runningCount.setStatus(newStatus);
        runningRepository.save(runningCount);
    }

    //Внутренний метод , расставляет места участникам забега
    private void calcRunningResultPlaces(Integer teamsRunningCountId){
        List<RunningResult> runningResultList = runningResultRepository.findRunningResultByTeamRunningCountId(teamsRunningCountId);
        Collections.sort(runningResultList, Comparator.comparing(RunningResult::getResult));
        Integer place=0;
        for (RunningResult runningResult : runningResultList) {
            place++;
            runningResult.setFinishPlace(place);
        }
    }

}
