package ru.runnerlite.services.interfaces;

import org.springframework.data.repository.query.Param;
import ru.runnerlite.entities.dto.MyAchievementsDto;
import ru.runnerlite.entities.dto.PlanRunDto;

import java.security.Principal;
import java.time.Instant;
import java.util.List;

public interface IPlanRunService {
    List<PlanRunDto> findUniqPlanRunUser(String currentUserName);
    void deleteRunnerFromRun(Integer runningCountId);
    void insertRunnerFromRun(String currentUserName, Integer teamsRunningCountId);
}
