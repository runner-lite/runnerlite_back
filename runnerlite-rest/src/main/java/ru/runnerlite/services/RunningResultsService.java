package ru.runnerlite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.RefCity;
import ru.runnerlite.entities.RunningResult;
import ru.runnerlite.entities.dto.RefCitiesDto;
import ru.runnerlite.entities.dto.RunningResultDto;
import ru.runnerlite.entities.dto.SecUserDto;
import ru.runnerlite.entities.dto.TeamsRunningCountDto;
import ru.runnerlite.repositories.RunningResultRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RunningResultsService implements IRunningResultsService {

    @Autowired
    RunningResultRepository runningResultRepository;

    @Override
    public List<RunningResultDto> findAll() {
        List<RunningResult> runningResult = runningResultRepository.findAll();
        List<RunningResultDto> runningResultDtos = runningResult.stream()
                .map(r -> new RunningResultDto(r.getId(), new SecUserDto(r.getSecUsers()), r.getResult(), new TeamsRunningCountDto(r.getTeamsRunningCount()), r.getFinishPlace())).collect(Collectors.toList());
        return runningResultDtos;
    }
}
