package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.runnerlite.entities.dto.PlanVolunteerDto;
import ru.runnerlite.services.PlanVolunteerService;
import ru.runnerlite.services.interfaces.IPlanVolunteerService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/planVolunteer")
public class PlanVolunteerController {

    private IPlanVolunteerService planVolunteerService;

    @Autowired
    public PlanVolunteerController(PlanVolunteerService planVolunteerService) {
        this.planVolunteerService = planVolunteerService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/get")
    public List<PlanVolunteerDto> getPlanVolunteerDto(Principal principal, Integer teamsRunningCountId) {
        String currentUserName = principal.getName();
        return planVolunteerService.findPlanVolunteer(currentUserName, teamsRunningCountId);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{volunteersId}/deleteVolunteerFromRun")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVolunteerFromRun(@PathVariable("volunteersId") Integer volunteersId){
        planVolunteerService.deleteVolunteerFromRun(volunteersId);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/insertVolunteerFromRun")
    public void insertVolunteerFromRun(Principal principal, Integer teamsRunningCountId, Integer volunteersPosition){
        String currentUserName = principal.getName();
        planVolunteerService.insertVolunteerFromRun(currentUserName, teamsRunningCountId, volunteersPosition);
    }
}
