package ru.runnerlite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.dto.PlanRunDto;
import ru.runnerlite.repositories.TeamsRunningCountRepository;
import ru.runnerlite.services.interfaces.IPlanRunService;

import java.util.List;

@Service
public class PlanRunService implements IPlanRunService {

    TeamsRunningCountRepository teamsRunningCountRepository;

    @Autowired
    public PlanRunService(TeamsRunningCountRepository teamsRunningCountRepository) {
        this.teamsRunningCountRepository = teamsRunningCountRepository;
    }

    @Override
    public List<PlanRunDto> findPlanRunUser(String currentUserName) {
        List<PlanRunDto> planRunDto = teamsRunningCountRepository.findPlanRunByUserName(currentUserName);
        if (planRunDto == null) {
            throw new IllegalArgumentException("Пользователь с id = " + currentUserName + " не найден.");
        }
        return planRunDto;
    }
}
