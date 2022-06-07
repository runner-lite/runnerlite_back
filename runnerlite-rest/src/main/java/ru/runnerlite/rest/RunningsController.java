package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.runnerlite.entities.dto.RunningPlaningDto;
import ru.runnerlite.services.interfaces.IRunningResultsService;

import java.util.List;

@RestController
@RequestMapping("/runnings")
public class RunningsController {

    private IRunningResultsService runningResultsService;

    @Autowired
    public RunningsController(IRunningResultsService runningResultsService) {
        this.runningResultsService = runningResultsService;
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
}
