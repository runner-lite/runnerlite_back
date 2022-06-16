package ru.runnerlite.rest;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.runnerlite.entities.dto.RunningPrepareStatus;
import ru.runnerlite.entities.dto.TeamsRunningCountDto;
import ru.runnerlite.services.RunningPrepareService;

import java.util.List;

@RestController
@RequestMapping("/runnings/prepare")
@NoArgsConstructor
public class RunningPrepareController {
	
	private RunningPrepareService runningPrepareService;
	
	@Autowired
	public RunningPrepareController(RunningPrepareService runningPrepareService) {
		this.runningPrepareService = runningPrepareService;
	}
	
	@GetMapping
	@RequestMapping("/status")
	public List<RunningPrepareStatus> getStatus(@RequestParam("teamId") Integer teamId,
	                                            @Nullable @RequestParam("count") Integer count,
	                                            @Nullable @RequestParam("dateFrom") String dateFrom) {
		return runningPrepareService.getStatus(teamId, count, dateFrom);
	}
	
	@GetMapping
	@RequestMapping("/getNewRunningNumber")
	public Integer getNewRunningNumber(@RequestParam("teamId") Integer teamId) {
		return runningPrepareService.getNewRunningNumber(teamId);
	}
	
	@PutMapping
	@RequestMapping("/create")
	public TeamsRunningCountDto createNewRunning(@RequestBody TeamsRunningCountDto newRunning) {
		return runningPrepareService.createNewRunning(newRunning);
	}
}
