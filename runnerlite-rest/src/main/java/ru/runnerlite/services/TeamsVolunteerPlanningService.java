package ru.runnerlite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.dto.*;
import ru.runnerlite.repositories.TeamsManagementRepository;
import ru.runnerlite.repositories.TeamsVolunteerRepository;
import ru.runnerlite.repositories.VolunteerRepository;
import ru.runnerlite.services.interfaces.ITeamsVolunteerPlanningService;

import java.util.List;

@Service
public class TeamsVolunteerPlanningService implements ITeamsVolunteerPlanningService {

    private TeamsManagementRepository teamsManagementRepository;
    private VolunteerRepository volunteerRepository;
    private TeamsVolunteerRepository teamsVolunteerRepository;

    @Autowired
    public TeamsVolunteerPlanningService(TeamsManagementRepository teamsManagementRepository, VolunteerRepository volunteerRepository, TeamsVolunteerRepository teamsVolunteerRepository) {
        this.teamsManagementRepository = teamsManagementRepository;
        this.volunteerRepository = volunteerRepository;
        this.teamsVolunteerRepository = teamsVolunteerRepository;
    }

    @Override
    public List<TeamsVolunteerPlanningDto> findTeamsVolunteerPlanningDto(String currentUserName){
        List<TeamsVolunteerPlanningDto> teamsVolunteerPlanningDto = teamsManagementRepository.findTeamsVolunteerPlanningDto(currentUserName);
        //прохожу по каждому TeamsVolunteerPlanningDto
        for (int i = 0; i < teamsVolunteerPlanningDto.size(); i++) {
            if (!(i == 3)) {
                List<TeamsVolunteerDto> teamsVolunteerDtoList = teamsVolunteerRepository.findTeamVolunteerDto(teamsVolunteerPlanningDto.get(i).getTeamsRunningCountId());
                // в каждом TeamsVolunteerPlanningDto беру TeamsVolunteerDto и прохожу по каждому
                for (int j = 0; j < teamsVolunteerDtoList.size(); j++) {
                    List<VolunteerPlanningDto> volunteerPlanningDtoList = volunteerRepository.findVolunteerForTeamsVolunteerDto(teamsVolunteerPlanningDto.get(i).getTeamsRunningCountId(), teamsVolunteerDtoList.get(j).getRefVolunteersPositionId());
                    // в каждом TeamsVolunteerDto беру VolunteerDto и прохожу по каждому
                    teamsVolunteerDtoList.get(j).setVolunteerDtoList(volunteerPlanningDtoList);
                }
                teamsVolunteerPlanningDto.get(i).setTeamsVolunteerDto(teamsVolunteerDtoList);
            }
            else break;
        }
        return teamsVolunteerPlanningDto;
    }

    @Override
    public void changeVolunteerStatus(Integer volunteersId, Integer status) {
        volunteerRepository.changeVolunteerStatus(volunteersId,status);
    }
}
