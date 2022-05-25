package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.runnerlite.entities.dto.TeamsVolunteerPlanningDto;
import ru.runnerlite.services.TeamsVolunteerPlanningService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/planVolunteersOnRun")
public class TeamsVolunteerPlanningController {

    private TeamsVolunteerPlanningService teamsVolunteerPlanningService;

    @Autowired
    public TeamsVolunteerPlanningController(TeamsVolunteerPlanningService teamsVolunteerPlanningService) {
        this.teamsVolunteerPlanningService = teamsVolunteerPlanningService;
    }

    @GetMapping()
    public List<TeamsVolunteerPlanningDto> planVolunteersOnRun(Principal principal) {
        String currentUserName = principal.getName();
        return teamsVolunteerPlanningService.findTeamsVolunteerPlanningDto(currentUserName);
    }

    @GetMapping("/{volunteersId}/{status}")
    public void insertVolunteerFromRun(@PathVariable("volunteersId") Integer volunteersId, @PathVariable("status") Integer status){
        teamsVolunteerPlanningService.changeVolunteerStatus(volunteersId, status);
    }
}
