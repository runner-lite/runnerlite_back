package ru.runnerlite.entities.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class MyAchievementsDto implements Serializable {
    private final Integer runningNumber; //номер забега
    private final Instant runningDate; //дата забега
    private final String teamsName; //имя команды
    private final Integer teamsId; //для логотипа команды
    private final String achievementName; //наименование достижения
    private final Integer achievementId; //для миниатюры достижения
    private final Integer finishPlace; //место в забеге с достижением
    private final Instant result; // результат (время) в забеге с достижением
}