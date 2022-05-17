package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Data
@Setter
@Getter
@AllArgsConstructor
public class PlanRunDto implements Serializable {
    private Integer teamsRunningCountId; // id строки из таблицы TeamsRunningCount
    private Integer participationStatus; // статус участия в забеге
    private String runningStatus; //статус забега
    private Instant runningDate; //дата забега
    private String teamsName; //имя команды
    private String descriptionTeams; //описание команды
    private Integer runningNumber; //номер забега
}
