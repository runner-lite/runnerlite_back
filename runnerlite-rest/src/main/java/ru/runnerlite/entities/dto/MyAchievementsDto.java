package ru.runnerlite.entities.dto;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyAchievementsDto implements Serializable {
    private Integer runningNumber; //номер забега
    private Instant runningDate; //дата забега
    private String teamsName; //имя команды
    private Integer teamsId; //для логотипа команды
    private String achievementName; //наименование достижения
    private Integer achievementId; //для миниатюры достижения
    private Integer finishPlace; //место в забеге с достижением
    private Integer result; // результат (время) в забеге с достижением
}