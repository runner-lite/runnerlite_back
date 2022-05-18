package ru.runnerlite.entities.dto;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

//таблица позволяющая записаться на забег волонтером

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanVolunteerDto implements Serializable {

    private Integer teamsRunningCountId; //id строки из таблицы TeamsRunningCount (нужна для участия в забеге волонтером)
    private Instant runningDate; //дата забега
    private Integer volunteersPositionId; //id строки из таблицы RefVolunteersPosition (нужна для участия в забеге волонтером)
    private Integer runningNumber; //номер забега
    private String positionName; //позиция волонтера
    private String positionDescription; //описание позиции волонтера
    private Integer positionRequired; // необходимое колличество волонтеров на указанную позицию
    private Integer id; //id строки в таблице волонтеров Volunteer соответствующая user занявшего позицию волонтера (нужна отмены участия в забеге)
    private List<String> fullNameVolunteer; // полное имя user'a занявшего позицию волонтера
    private Integer participationStatus; // статус позиции пользователя для участия в забеге (пусто если заявился или заявиться)
    private Boolean statusRunner; // проверка участия текущего пользователя в качестве бегуна true - участвует, false - не участвует


    public PlanVolunteerDto(Integer teamsRunningCountId, Instant runningDate, Integer volunteersPositionId, Integer runningNumber, String positionName, String positionDescription, Integer positionRequired) {
        this.teamsRunningCountId = teamsRunningCountId;
        this.runningDate = runningDate;
        this.volunteersPositionId = volunteersPositionId;
        this.runningNumber = runningNumber;
        this.positionName = positionName;
        this.positionDescription = positionDescription;
        this.positionRequired = positionRequired;
    }
}
