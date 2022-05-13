package ru.runnerlite.entities.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class PlanRunDto implements Serializable {
    private final Integer participationStatus; // статус участия в забеге
    private final String runningStatus; //статус забега
    private final Instant runningDate; //дата забега
    private final String teamsName; //имя команды
    private final String descriptionTeams; //описание команды
    private final Integer runningNumber; //номер забега
}
