package ru.runnerlite.services.interfaces;

import ru.runnerlite.entities.dto.PlanRunDto;

import java.util.List;

public interface IPlanRunService {
    List<PlanRunDto> findPlanRunUser(String currentUserName);
}
