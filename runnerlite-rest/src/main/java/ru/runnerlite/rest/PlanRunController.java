package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.runnerlite.entities.dto.PlanRunDto;
import ru.runnerlite.services.interfaces.IPlanRunService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/planRun")
public class PlanRunController {

    private IPlanRunService planRunService;

    @Autowired
    public PlanRunController(IPlanRunService planRunService) {
        this.planRunService = planRunService;
    }

    @GetMapping()
    public List<PlanRunDto> getPlanRunDto(Principal principal) {
        String currentUserName = principal.getName();
        return planRunService.findUniqPlanRunUser(currentUserName);
    }

    @PreAuthorize("@A.itsOwnRunningRequest(principal,#runningCountId)")
    @DeleteMapping("/{runningCountId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRunnerFromRun(@PathVariable("runningCountId") Integer runningCountId){
        planRunService.deleteRunnerFromRun(runningCountId);
    }

    @PostMapping("/{teamsRunningCountId}")
    @ResponseStatus(HttpStatus.OK)
    public void insertRunnerFromRun(Principal principal, @PathVariable("teamsRunningCountId") Integer teamsRunningCountId){
        String currentUserName = principal.getName();
        planRunService.insertRunnerFromRun(currentUserName, teamsRunningCountId);
    }
}
