package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.runnerlite.entities.dto.RunningResultTableDto;
import ru.runnerlite.services.interfaces.IRunningResultTableService;

@RestController
@RequestMapping("/tableResults")
public class RunningResultTableController {

    private IRunningResultTableService runningResultsTableService;

    @Autowired
    public RunningResultTableController(IRunningResultTableService runningResultsTableService) {
        this.runningResultsTableService = runningResultsTableService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{teamRunning}")
    public RunningResultTableDto getAllResults(@PathVariable("teamRunning") Integer teamRunning) {
        return runningResultsTableService.findAllResultByTeamRunning(teamRunning);
    }
}
