package ru.runnerlite.entities.dto;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanRunDto implements Serializable {
    private Integer teamsRunningCountId; // id строки из таблицы TeamsRunningCount (нужна для участия в забеге)
    private Integer participationStatus; // статус участия в забеге 1 - участвует, 0 - не участвует
    private String runningStatus; //статус забега
    private Instant runningDate; //дата забега
    private String teamsName; //имя команды
    private String descriptionTeams; //описание команды
    private Integer runningNumber; //номер забега
    private Integer runnersCount; //количество бегунов участвующих в забеге
    private Integer volunteersCount; //количество волонтеров участвующих в забеге
    private Integer runnerCountId; // id строки из таблицы RunnerCount (нужна для отмены участия в забеге)
    private Integer statusVolunteer; // проверка участия бегуна в качестве волонтера 0 - запрос, 1 - принято, 2 - отказано

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
