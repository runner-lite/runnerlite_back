package ru.runnerlite.services.interfaces;

import ru.runnerlite.entities.dto.TeamsVolunteerPlanningDto;

import java.util.List;

public interface ITeamsVolunteerPlanningService {

    List<TeamsVolunteerPlanningDto> findTeamsVolunteerPlanningDto(String currentUserName);
    void changeVolunteerStatus(Integer volunteersId, Integer status);
}
