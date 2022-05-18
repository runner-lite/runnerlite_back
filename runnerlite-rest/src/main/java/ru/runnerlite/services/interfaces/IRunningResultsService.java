package ru.runnerlite.services.interfaces;

import org.springframework.data.repository.query.Param;
import ru.runnerlite.entities.dto.RunningResultDto;

import java.util.List;

public interface IRunningResultsService {
        List<RunningResultDto> findAllResult();
        RunningResultDto getLastRunningResult(String currentUserName);
        Integer historicalVolunteerismCount (@Param("currentUserName") String currentUserName);
        Integer historicalRunnerCount (@Param("currentUserName") String currentUserName);
    }