package ru.runnerlite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.dto.RunningResultDto;
import ru.runnerlite.entities.dto.TeamsRunningCountDto;
import ru.runnerlite.repositories.*;
import ru.runnerlite.services.interfaces.IRunningResultsService;

import java.util.List;

@Service
public class RunningResultsService implements IRunningResultsService {

    private final RunningResultRepository runningResultRepository;
    private final SecUserService userService;
    private final TeamsRunningCountRepository runningRepository;
    private final SecUserRepository secUserRepository;
    private final RunnerCountRepository runnerCountRepository;
    private final VolunteerRepository volunteerRepository;

    @Autowired
    public RunningResultsService(RunningResultRepository runningResultRepository, SecUserService userService, TeamsRunningCountRepository runningRepository, SecUserRepository secUserRepository, RunnerCountRepository runnerCountRepository, VolunteerRepository volunteerRepository) {
        this.runningResultRepository = runningResultRepository;
        this.userService = userService;
        this.runningRepository = runningRepository;
        this.secUserRepository = secUserRepository;
        this.runnerCountRepository = runnerCountRepository;
        this.volunteerRepository = volunteerRepository;
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
}
