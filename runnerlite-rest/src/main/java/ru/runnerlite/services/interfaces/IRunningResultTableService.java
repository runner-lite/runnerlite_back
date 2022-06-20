package ru.runnerlite.services.interfaces;

import ru.runnerlite.entities.dto.RunningResultTableDto;


public interface IRunningResultTableService {

    RunningResultTableDto findAllResultByTeamRunning(Integer teamRunningId);
}
