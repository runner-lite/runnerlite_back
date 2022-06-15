package ru.runnerlite.services.interfaces;

import ru.runnerlite.entities.dto.ChangeTableDto;
import ru.runnerlite.entities.dto.TeamsRunningCountDto;
import ru.runnerlite.entities.dto.UserNameDto;

import java.text.ParseException;
import java.util.List;

public interface IChangeDataRunningResultTableService {

    TeamsRunningCountDto findTeamsRunningCountByRunningDate(String currentUserName);
    List<ChangeTableDto> findListRunningResult(String currentUserName);
    List<UserNameDto> findAllRunnerId(String currentUserName);
    boolean insertRunnerResult (String currentUserName, Integer userId, String result) throws ParseException;
//  void changeTeamsRunningCountStatus(String currentUserName, Integer runningNumber);

}
