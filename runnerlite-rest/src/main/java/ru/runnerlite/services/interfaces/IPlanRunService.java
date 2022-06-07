package ru.runnerlite.services.interfaces;

import ru.runnerlite.entities.dto.PlanRunDto;

import java.util.List;

public interface IPlanRunService {
    List<PlanRunDto> findUniqPlanRunUser(String currentUserName);
    void deleteRunnerFromRun(Integer runningCountId);
    void insertRunnerFromRun(String currentUserName, Integer teamsRunningCountId);
}
