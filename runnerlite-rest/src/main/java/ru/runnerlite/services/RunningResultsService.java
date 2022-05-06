package ru.runnerlite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.RunningResult;
import ru.runnerlite.entities.SecUser;
import ru.runnerlite.entities.TeamsRunningCount;
import ru.runnerlite.entities.dto.RunningResultDto;
import ru.runnerlite.entities.dto.TeamsRunningCountDto;
import ru.runnerlite.repositories.RunningResultRepository;
import ru.runnerlite.repositories.SecUserRepository;
import ru.runnerlite.repositories.TeamsRunningCountRepository;
import ru.runnerlite.services.interfaces.IRunningResultsService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RunningResultsService implements IRunningResultsService {


    private final RunningResultRepository runningResultRepository;
    

    private final SecUserService userService;


    private final TeamsRunningCountRepository runningRepository;

    private final SecUserRepository secUserRepository;

    @Autowired
    public RunningResultsService(RunningResultRepository runningResultRepository, SecUserService userService, TeamsRunningCountRepository runningRepository, SecUserRepository secUserRepository) {
        this.runningResultRepository = runningResultRepository;
        this.userService = userService;
        this.runningRepository = runningRepository;
        this.secUserRepository = secUserRepository;
    }

    @Override
    public List<RunningResultDto> findAll() {
        List<RunningResult> runningResult = runningResultRepository.findAll();
        return runningResult.stream()
            .map(this::convert)
            .collect(Collectors.toList());
    }
    
    @Override
    public RunningResultDto getLastRunningResult(Integer userId) {
        RunningResult runningResult = runningResultRepository.findFirstBySecUser_IdOrderByIdDesc(userId);
        if (runningResult == null) {
            throw new IllegalArgumentException("Пользователь с id = " + userId + " не найден.");
        }
        return convert(runningResult);
    }
    
    public RunningResultDto convert(RunningResult result) {
        return new RunningResultDto(
            result.getId(),
            result.getTeamsRunningCount().getId(),
            result.getSecUser().getId(),
            result.getFinishPlace(),
            result.getResult()
        );
    }
    
    public RunningResult convert(RunningResultDto result) {
        SecUser user = secUserRepository.getById(result.getUserId().longValue());
        TeamsRunningCount running = runningRepository.getById(result.getRunningId());
        return new RunningResult(result.getId(),
            user,
            result.getResult(),
            running,
            result.getFinishPlace()
        );
    }
}
