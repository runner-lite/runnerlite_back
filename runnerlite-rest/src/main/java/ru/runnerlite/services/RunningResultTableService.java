package ru.runnerlite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.dto.RunningResultTableDto;
import ru.runnerlite.entities.dto.TourneyTableDto;
import ru.runnerlite.repositories.RunnerCountRepository;
import ru.runnerlite.repositories.RunningResultRepository;
import ru.runnerlite.repositories.TeamsRunningCountRepository;
import ru.runnerlite.services.interfaces.IRunningResultTableService;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;

@Service
public class RunningResultTableService implements IRunningResultTableService {

    private final RunningResultRepository runningResultRepository;
    private final RunnerCountRepository runnerCountRepository;
    private final TeamsRunningCountRepository teamsRunningCountRepository;
    @Autowired
    public RunningResultTableService(RunningResultRepository runningResultRepository, RunnerCountRepository runnerCountRepository, TeamsRunningCountRepository teamsRunningCountRepository) {
        this.runningResultRepository = runningResultRepository;
        this.runnerCountRepository = runnerCountRepository;
        this.teamsRunningCountRepository = teamsRunningCountRepository;
    }

    @Override
    public RunningResultTableDto findAllResultByTeamRunning(Integer teamRunningId) {
        Instant runningDate = teamsRunningCountRepository.getRunningDate(teamRunningId);
        Integer runningNumber = teamsRunningCountRepository.getRunningNumber(teamRunningId);
        Integer countRunners = runnerCountRepository.countRunnersByTeamsRunningCountNumber(teamRunningId);
        List<TourneyTableDto> outList = runningResultRepository.findAllResultByTeamRunning(teamRunningId);
        if(outList.isEmpty()){
            System.out.println("По забегу c id " + teamRunningId + " отсутствуют данные с результатами.");
            return null;
        }
        for (int i = 0; i < outList.size(); i++) {
                outList.get(i).setResultString(calculateInMinute(outList.get(i).getResult()));
        }
        RunningResultTableDto runningResultTableDto = new RunningResultTableDto();
        runningResultTableDto.setRunningDate(runningDate);
        runningResultTableDto.setRunningNumber(runningNumber);
        runningResultTableDto.setRunnersCount(countRunners);
        runningResultTableDto.setTourneyTableDto(outList);
        return runningResultTableDto;
    }

    public String calculateInMinute(Integer seconds) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.set(Calendar.SECOND, seconds);
        if (seconds<3600) {
            return new SimpleDateFormat("mm:ss").format(calendar.getTime());
        }
        else  return new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
    }
}
