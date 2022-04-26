package ru.runnerlite.services;

import ru.runnerlite.entities.dto.RunningResultDto;

import java.util.List;

public interface IRunningResultsService {
        List<RunningResultDto> findAll();
    }