package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.runnerlite.entities.dto.RunningResultDto;
import ru.runnerlite.services.IRunningResultsService;

import java.util.List;

@RestController
@RequestMapping("/results")
public class RunningResultsController {

    @Autowired
    IRunningResultsService runningResultsService;

    @GetMapping("/getAll")
    public List<RunningResultDto> getAllResults() {
        return runningResultsService.findAll();
    }
}
