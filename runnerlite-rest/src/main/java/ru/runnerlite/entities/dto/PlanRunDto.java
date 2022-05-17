package ru.runnerlite.entities.dto;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanRunDto implements Serializable {
    private Integer teamsRunningCountId; // id строки из таблицы TeamsRunningCount (нужна для участия в забеге)
    private Integer participationStatus; // статус участия в забеге
    private String runningStatus; //статус забега
    private Instant runningDate; //дата забега
    private String teamsName; //имя команды
    private String descriptionTeams; //описание команды
    private Integer runningNumber; //номер забега
    private Integer runnersCount; //количество бегунов участвующих в забеге
    private Integer volunteersCount; //количество волонтеров участвующих в забеге
    private Integer runnerCountId; // id строки из таблицы RunnerCount (нужна отмены участия в забеге)

    public PlanRunDto(Integer teamsRunningCountId, Integer participationStatus, String runningStatus, Instant runningDate, String teamsName, String descriptionTeams, Integer runningNumber) {
        this.teamsRunningCountId = teamsRunningCountId;
        this.participationStatus = participationStatus;
        this.runningStatus = runningStatus;
        this.runningDate = runningDate;
        this.teamsName = teamsName;
        this.descriptionTeams = descriptionTeams;
        this.runningNumber = runningNumber;
    }
}
