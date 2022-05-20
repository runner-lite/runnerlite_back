package ru.runnerlite.services.interfaces;

import ru.runnerlite.entities.dto.RunningResultDto;
import ru.runnerlite.entities.dto.TeamsRunningCountDto;

import java.util.List;

public interface IRunningResultsService {
        List<RunningResultDto> findAllResult();
        RunningResultDto getLastRunningResult(String currentUserName);
        List<TeamsRunningCountDto> getTeamRunningResults(Integer teamId);
    }