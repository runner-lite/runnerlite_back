package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.runnerlite.entities.dto.InsertVolunteerDto;
import ru.runnerlite.entities.dto.PlanVolunteerDto;
import ru.runnerlite.services.PlanVolunteerService;
import ru.runnerlite.services.interfaces.IPlanVolunteerService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/planVolunteer")
@PreAuthorize("isAuthenticated()")
public class PlanVolunteerController {

    private IPlanVolunteerService planVolunteerService;

    @Autowired
    public PlanVolunteerController(PlanVolunteerService planVolunteerService) {
        this.planVolunteerService = planVolunteerService;
    }

    @GetMapping("/{teamsRunningCountId}")
    public List<PlanVolunteerDto> getPlanVolunteerDto(Principal principal, @PathVariable("teamsRunningCountId") Integer teamsRunningCountId) {
        String currentUserName = principal.getName();
        return planVolunteerService.findPlanVolunteer(currentUserName, teamsRunningCountId);
    }

    @PreAuthorize("@A.itsOwnVolunteerRequest(principal,#volunteersId)")
    @DeleteMapping("/{volunteersId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVolunteerFromRun(@PathVariable("volunteersId") Integer volunteersId){
        planVolunteerService.deleteVolunteerFromRun(volunteersId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public void insertVolunteerFromRun(Principal principal, @Valid @RequestBody InsertVolunteerDto dto){
        String currentUserName = principal.getName();
        planVolunteerService.insertVolunteerFromRun(currentUserName, dto.getTeamsRunningCountId(), dto.getVolunteersPosition());
    }
}
