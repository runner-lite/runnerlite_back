package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.runnerlite.entities.dto.*;
import ru.runnerlite.services.ChangeDataRunningResultTableService;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/changeTableResults")
public class ChangeDataRunningResultTableController {

    private ChangeDataRunningResultTableService changeDataRunningResultTableService;

    @Autowired
    public ChangeDataRunningResultTableController(ChangeDataRunningResultTableService changeDataRunningResultTableService) {
        this.changeDataRunningResultTableService = changeDataRunningResultTableService;
    }

    //поиск забега для внесения изменений в таблицу результатов
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getTeamsRunningCount")
    TeamsRunningCountDto findTeamsRunningCountByRunningDate(Principal principal) {
        String currentUserName = principal.getName();
        return changeDataRunningResultTableService.findTeamsRunningCountByRunningDate(currentUserName);
    }

    //получение списка внесенных результатов забега
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getAllResult")
    @ResponseStatus(HttpStatus.OK)
    List<ChangeTableDto> findListRunningResult(Principal principal) {
        String currentUserName = principal.getName();
        return changeDataRunningResultTableService.findListRunningResult(currentUserName);
    }

    //получение списка пользователей по которым не внесены результаты забега
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getAllRunner")
    @ResponseStatus(HttpStatus.OK)
    public List<UserNameDto> getAllRunner(Principal principal) {
        String currentUserName = principal.getName();
        return changeDataRunningResultTableService.findAllRunnerId(currentUserName);
    }

    //внесение результатов забега по определенному бегуну
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/insertRunnerResult")
    @ResponseStatus(HttpStatus.OK)
    public boolean insertRunnerResult(Principal principal,
                                      @RequestParam("userId") Integer userId,
                                      @RequestParam("result") String result) throws ParseException {
        String currentUserName = principal.getName();
        return changeDataRunningResultTableService.insertRunnerResult(currentUserName, userId, result);
    }

//    //Закрытие таблицы после внесения результатов забега
//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/closeTable")
//    public void changeTeamsRunningCountStatus(Principal principal, @RequestParam("runningNumber")  Integer runningNumber) {
//        String currentUserName = principal.getName();
//        changeDataRunningResultTableService.changeTeamsRunningCountStatus(currentUserName, runningNumber);
//    }
}
