package ru.runnerlite.services.interfaces;

import ru.runnerlite.entities.dto.PlanVolunteerDto;

import java.util.List;

public interface IPlanVolunteerService {
    List<PlanVolunteerDto> findPlanVolunteer (Integer teamsRunningCountId);
    void deleteVolunteerFromRun(Integer volunteersId);
    void insertVolunteerFromRun(String currentUserName, Integer teamsRunningCountId, Integer volunteersPosition);
}
