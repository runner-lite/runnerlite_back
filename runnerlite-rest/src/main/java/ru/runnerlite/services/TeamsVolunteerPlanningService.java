package ru.runnerlite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.dto.TeamsVolunteerDto;
import ru.runnerlite.entities.dto.TeamsVolunteerPlanningDto;
import ru.runnerlite.entities.dto.VolunteerPlanningDto;
import ru.runnerlite.repositories.TeamsManagementRepository;
import ru.runnerlite.repositories.TeamsVolunteerRepository;
import ru.runnerlite.repositories.VolunteerRepository;
import ru.runnerlite.services.interfaces.ITeamsVolunteerPlanningService;

import java.util.List;

@Service
public class TeamsVolunteerPlanningService implements ITeamsVolunteerPlanningService {

    private final TeamsManagementRepository teamsManagementRepository;
    private final VolunteerRepository volunteerRepository;
    private final TeamsVolunteerRepository teamsVolunteerRepository;

    private LetterService letterService;

    @Autowired
    public TeamsVolunteerPlanningService(TeamsManagementRepository teamsManagementRepository,
                                         VolunteerRepository volunteerRepository,
                                         TeamsVolunteerRepository teamsVolunteerRepository,
                                         LetterService letterService) {
        this.teamsManagementRepository = teamsManagementRepository;
        this.volunteerRepository = volunteerRepository;
        this.teamsVolunteerRepository = teamsVolunteerRepository;
        this.letterService=letterService;
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
        //отправка письма автору заявки с результатом рассмотрения
        if(status.equals(1)){
            letterService.sendVolunteerAcceptLetter(volunteersId,"принята");
        }
        else if(status.equals(2)){
            letterService.sendVolunteerAcceptLetter(volunteersId,"отклонена");
        }
    }
}
