package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    IRunningResultsService runningResultsService;

    @GetMapping("/getAll")
    public List<RunningResultDto> getAllResults() {
        return runningResultsService.findAllResult();
    }
    
    @GetMapping("/last")
    public RunningResultDto getLastRunningResult(Principal principal) {
        String currentUserName = principal.getName();
        return runningResultsService.getLastRunningResult(currentUserName);
    }
    
}
