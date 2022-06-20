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
import ru.runnerlite.repositories.SecUserRepository;
import ru.runnerlite.services.RunningPrepareService;
import ru.runnerlite.services.interfaces.IRunningResultsService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/runnings")
public class RunningsController {

    private final IRunningResultsService runningResultsService;
    private final RunningPrepareService runningPrepareService;
    private final SecUserRepository secUserRepository;
    
    @Autowired
    public RunningsController(IRunningResultsService runningResultsService, RunningPrepareService runningPrepareService, SecUserRepository secUserRepository) {
        this.runningResultsService = runningResultsService;
        this.runningPrepareService = runningPrepareService;
        this.secUserRepository = secUserRepository;
    }

    @GetMapping
    public List<RunningPlaningDto> getTeamRunningStatisticWithLimit(@RequestParam("teamId") Integer teamId,
                                                                    @RequestParam("count") Integer count){
        return runningResultsService.getTeamRunningStatisticWithLimit(teamId,count);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RunningPlaningDto> putTeamRunningStatistic(Principal principal,
                                                           @RequestParam("runningId")Integer runningId,
                                                           @RequestParam("newStatus")String newStatus){
        String userName = principal.getName();
        Integer teamId = secUserRepository.findTeamByUsername(userName).orElse(-1);
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
    
    @PostMapping
    @RequestMapping("/save")
    public TeamsRunningCountDto save(Principal principal, @RequestBody TeamsRunningCountDto running) {
        String userName = principal.getName();
        Integer teamId = secUserRepository.findTeamByUsername(userName).orElse(-1);
        if (teamId <= 0) {
            return new TeamsRunningCountDto();
        }
        running.setTeamId(teamId);
        if (running.getNumber() == null) {
            Integer number = runningPrepareService.getNewRunningNumber(teamId);
            running.setNumber(number);
        }
        return runningPrepareService.save(running);
    }
}
