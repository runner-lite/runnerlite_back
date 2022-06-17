package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.runnerlite.entities.dto.RunningPlaningDto;
import ru.runnerlite.entities.dto.RunningPrepareStatus;
import ru.runnerlite.entities.dto.TeamsRunningCountDto;
import ru.runnerlite.services.RunningPrepareService;
import ru.runnerlite.services.interfaces.IRunningResultsService;

import java.util.List;

@RestController
@RequestMapping("/runnings")
public class RunningsController {

    private final IRunningResultsService runningResultsService;
    private final RunningPrepareService runningPrepareService;

    @Autowired
    public RunningsController(IRunningResultsService runningResultsService, RunningPrepareService runningPrepareService) {
        this.runningResultsService = runningResultsService;
        this.runningPrepareService = runningPrepareService;
    }

    @GetMapping
    public List<RunningPlaningDto> getTeamRunningStatisticWithLimit(@RequestParam("teamId") Integer teamId,
                                                                    @RequestParam("count") Integer count){
        return runningResultsService.getTeamRunningStatisticWithLimit(teamId,count);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RunningPlaningDto> putTeamRunningStatistic(@RequestParam("teamId") Integer teamId,
                                                           @RequestParam("runningId")Integer runningId,
                                                           @RequestParam("newStatus")String newStatus){
        if(newStatus!=null&&newStatus.equals("Запланирован")|| newStatus.equals("Выполнен")|| newStatus.equals("Отменён")|| newStatus.equals("Перенесён")){
        runningResultsService.changeStatusTeamRunningStatistic(teamId,runningId,newStatus);
        return runningResultsService.getTeamRunningStatistic(teamId);
        }
        else throw new IllegalArgumentException("Указан не допустимый параметер статуса "+newStatus);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String illegalArgumentHandler(IllegalArgumentException ex) {
        return ex.getMessage();
    }
    
    @GetMapping
    @RequestMapping("/prepare/status")
    public List<RunningPrepareStatus> getStatus(@RequestParam("teamId") Integer teamId,
                                                @Nullable @RequestParam("count") Integer count,
                                                @Nullable @RequestParam("dateFrom") String dateFrom) {
        return runningPrepareService.getStatus(teamId, count, dateFrom);
    }
    
    @GetMapping
    @RequestMapping("/prepare/getNewRunningNumber")
    public Integer getNewRunningNumber(@RequestParam("teamId") Integer teamId) {
        return runningPrepareService.getNewRunningNumber(teamId);
    }
    
    @PostMapping
    @RequestMapping("/save")
    public TeamsRunningCountDto save(@RequestBody TeamsRunningCountDto newRunning) {
        return runningPrepareService.save(newRunning);
    }
}
