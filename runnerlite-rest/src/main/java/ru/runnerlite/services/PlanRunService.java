package ru.runnerlite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.dto.PlanRunDto;
import ru.runnerlite.repositories.TeamsRunningCountRepository;
import ru.runnerlite.services.interfaces.IPlanRunService;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public List<PlanRunDto> findUniqPlanRunUser(String currentUserName) {
        List<PlanRunDto> planRunDto = findPlanRunUser(currentUserName);
        Map<Integer,PlanRunDto> uniqMapScheduled  = new HashMap<>();
        Map<Integer,PlanRunDto> uniqMapRescheduled  = new HashMap<>();
        for (PlanRunDto planRun: planRunDto) {
            if ("Запланирован".equals(planRun.getRunningStatus())){
                uniqMapScheduled.put(planRun.getRunningNumber(), planRun);
            }
            else uniqMapRescheduled.put(planRun.getRunningNumber(), planRun);
        }
        ArrayList<PlanRunDto> listUniqScheduled = uniqMapScheduled.values().stream().collect(Collectors.toCollection(ArrayList::new));
        ArrayList<PlanRunDto> listUniqRescheduled = uniqMapRescheduled.values().stream().collect(Collectors.toCollection(ArrayList::new));
        ArrayList<PlanRunDto> listUniq = new ArrayList<>(listUniqScheduled.size()+ listUniqRescheduled.size());
        listUniq.addAll(listUniqScheduled);
        listUniq.addAll(listUniqRescheduled);
        List<PlanRunDto> list = new ArrayList<>();
        for (PlanRunDto planRun:listUniq) {
            planRun.setParticipationStatus(planRun.getParticipationStatus() == null ? 0 : 1);
            list.add(planRun);
        }
        return list;
    }


}
