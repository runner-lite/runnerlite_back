package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.runnerlite.entities.dto.RunningResultDto;
import ru.runnerlite.services.interfaces.IRunningResultsService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/results")
public class RunningResultsController {

    private IRunningResultsService runningResultsService;

    @Autowired
    public RunningResultsController(IRunningResultsService runningResultsService) {
        this.runningResultsService = runningResultsService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getAll")
    public List<RunningResultDto> getAllResults() {
        return runningResultsService.findAllResult();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/last")
    public RunningResultDto getLastRunningResult(Principal principal) {
        String currentUserName = principal.getName();
        return runningResultsService.getLastRunningResult(currentUserName);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/volunteerismCount")
    public Integer historicalVolunteerismCount (Principal principal) {
        String currentUserName = principal.getName();
        return runningResultsService.historicalVolunteerismCount(currentUserName);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/runnerCount")
    public Integer historicalRunnerCount (Principal principal) {
        String currentUserName = principal.getName();
        return runningResultsService.historicalRunnerCount(currentUserName);
    }
}
