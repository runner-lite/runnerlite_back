package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamsVolunteerPlanningDto implements Serializable {

    private Integer teamsRunningCountId; //id строки из таблицы TeamsRunningCount
    private Instant runningDate; //дата забега
    private Integer runningNumber; //номер забега
    private String runningStatus; //статус забега
    private List<TeamsVolunteerDto> teamsVolunteerDto; //информация о необходимом кол-ве волонтеров на забег (и занятых позициях)

    public TeamsVolunteerPlanningDto(Integer teamsRunningCountId, Instant runningDate, Integer runningNumber, String runningStatus) {
        this.teamsRunningCountId = teamsRunningCountId;
        this.runningDate = runningDate;
        this.runningNumber = runningNumber;
        this.runningStatus = runningStatus;
    }
}
