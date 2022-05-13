package ru.runnerlite.services.interfaces;

import ru.runnerlite.entities.dto.MyAchievementsDto;

import java.util.List;

public interface IMyAchievementsService {
    List<MyAchievementsDto> findAchievementsUser(String currentUserName);
}
