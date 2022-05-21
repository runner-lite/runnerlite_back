package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/get")
    public List<PlanRunDto> getPlanRunDto(Principal principal) {
        String currentUserName = principal.getName();
        return planRunService.findUniqPlanRunUser(currentUserName);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{runningCountId}/deleteRunnerFromRun")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRunnerFromRun(@PathVariable("runningCountId") Integer runningCountId){
        planRunService.deleteRunnerFromRun(runningCountId);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/insertRunnerFromRun")
    public void insertRunnerFromRun(Principal principal, Integer teamsRunningCountId){
        String currentUserName = principal.getName();
        planRunService.insertRunnerFromRun(currentUserName, teamsRunningCountId);
    }
}
