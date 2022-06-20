package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
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

//    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{teamRunningId}")
    public RunningResultTableDto getAllResults(@PathVariable("teamRunningId") Integer teamRunningId) {
        return runningResultsTableService.findAllResultByTeamRunning(teamRunningId);
    }
}
