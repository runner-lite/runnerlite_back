package ru.runnerlite.entities.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
public class VolunteerDto  implements Serializable {
    private final Integer id; //id волонтера
    private final Integer userId; //id user
    private final Instant runningDate; //дата забега
    private final Integer runningNumber; //номер забега
    private final String positionName; //позиция волонтера
    private final String positionDescription; //описание позиции волонтера
    private final String teamsName; //имя команды
    private final Integer teamsId; //для логотипа
    private final Long runningNumberCount; //количество забегов в качестве волонтера
    private List<String> positionNameHistory; //исторический список позиций волонтера

    public VolunteerDto(Integer id, Integer userId, Instant runningDate, Integer runningNumber, String positionName, String positionDescription, String teamsName, Integer teamsId, Long runningNumberCount) {
        this.id = id;
        this.userId = userId;
        this.runningDate = runningDate;
        this.runningNumber = runningNumber;
        this.positionName = positionName;
        this.positionDescription = positionDescription;
        this.teamsName = teamsName;
        this.teamsId = teamsId;
        this.runningNumberCount = runningNumberCount;
    }

    public VolunteerDto(Integer id, Integer userId, Instant runningDate, Integer runningNumber, String positionName, String positionDescription, String teamsName, Integer teamsId, Long runningNumberCount, List<String> positionNameHistory) {
        this.id = id;
        this.userId = userId;
        this.runningDate = runningDate;
        this.runningNumber = runningNumber;
        this.positionName = positionName;
        this.positionDescription = positionDescription;
        this.teamsName = teamsName;
        this.teamsId = teamsId;
        this.runningNumberCount = runningNumberCount;
        this.positionNameHistory = positionNameHistory;
    }
}
