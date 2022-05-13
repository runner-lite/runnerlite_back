package ru.runnerlite.services.interfaces;

import ru.runnerlite.entities.dto.RunningResultDto;

import java.util.List;

public interface IRunningResultsService {
        List<RunningResultDto> findAllResult();
        RunningResultDto getLastRunningResult(String currentUserName);
    }