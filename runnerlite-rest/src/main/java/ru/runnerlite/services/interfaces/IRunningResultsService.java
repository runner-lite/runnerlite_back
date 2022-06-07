package ru.runnerlite.services.interfaces;

import org.springframework.data.repository.query.Param;
import ru.runnerlite.entities.dto.RunningPlaningDto;
import ru.runnerlite.entities.dto.RunningResultDto;
import ru.runnerlite.entities.dto.TeamsRunningCountDto;

import java.util.List;

public interface IRunningResultsService {
        List<RunningResultDto> findAllResult();
        RunningResultDto getLastRunningResult(String currentUserName);
        Integer historicalVolunteerismCount (@Param("currentUserName") String currentUserName);
        Integer historicalRunnerCount (@Param("currentUserName") String currentUserName);
        List<TeamsRunningCountDto> getTeamRunningResults(Integer teamId);
        List<RunningPlaningDto> getTeamRunningStatisticWithLimit(Integer teamId, Integer counter);
        List<RunningPlaningDto> getTeamRunningStatistic(Integer teamId);
        void changeStatusTeamRunningStatistic(Integer teamId,Integer runningId,String newStatus);
}