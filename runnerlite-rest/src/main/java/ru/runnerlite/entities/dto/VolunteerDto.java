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
    private final Integer runningNumberСount; //колчиство забегов в качестве волонтера
    private final List<String> positionNameHistory; //исторический список позиций волонтера
}
