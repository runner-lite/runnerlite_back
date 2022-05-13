package ru.runnerlite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.dto.MyAchievementsDto;
import ru.runnerlite.repositories.AchievementRepository;
import ru.runnerlite.services.interfaces.IMyAchievementsService;

import java.util.List;

@Service
public class MyAchievementsService implements IMyAchievementsService {

    AchievementRepository achievementRepository;

    @Autowired
    public MyAchievementsService(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    @Override
    public List<MyAchievementsDto> findAchievementsUser(String currentUserName) {
        List<MyAchievementsDto> mySubscriptionsDto = achievementRepository.findAchievementByUserName(currentUserName);
        if (mySubscriptionsDto == null) {
            throw new IllegalArgumentException("Пользователь с id = " + currentUserName + " не найден.");
        }
        return mySubscriptionsDto;
    }
}
