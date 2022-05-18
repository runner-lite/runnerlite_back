package ru.runnerlite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.RunnerCount;
import ru.runnerlite.entities.SecUser;
import ru.runnerlite.entities.TeamsRunningCount;
import ru.runnerlite.entities.dto.PlanRunDto;
import ru.runnerlite.repositories.RunnerCountRepository;
import ru.runnerlite.repositories.SecUserRepository;
import ru.runnerlite.repositories.TeamsRunningCountRepository;
import ru.runnerlite.repositories.VolunteerRepository;
import ru.runnerlite.services.interfaces.IPlanRunService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlanRunService implements IPlanRunService {

    private TeamsRunningCountRepository teamsRunningCountRepository;
    private RunnerCountRepository runnerCountRepository;
    private SecUserRepository secUserRepository;
    private VolunteerRepository volunteerRepository;

    @Autowired
    public PlanRunService(TeamsRunningCountRepository teamsRunningCountRepository, RunnerCountRepository runnerCountRepository, SecUserRepository secUserRepository, VolunteerRepository volunteerRepository) {
        this.teamsRunningCountRepository = teamsRunningCountRepository;
        this.runnerCountRepository = runnerCountRepository;
        this.secUserRepository = secUserRepository;
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public List<PlanRunDto> findPlanRunUser(String currentUserName) {
        List<PlanRunDto> planRunDto = teamsRunningCountRepository.findPlanRunByUserName(currentUserName);
        if (planRunDto == null) {
            throw new IllegalArgumentException("Пользователь с id = " + currentUserName + " не найден.");
        }
        return planRunDto;
    }

    //выбираем забеги по полю "номер", сортируем по дате (свежие) и возвращаем 3 забега
    @Override
    public List<PlanRunDto> findUniqPlanRunUser(String currentUserName) {
        List<PlanRunDto> planRunDto = findPlanRunUser(currentUserName);
        Map<Integer,PlanRunDto> uniqMapScheduled  = new HashMap<>();
        for (PlanRunDto planRun: planRunDto) {
            if ("Запланирован".equals(planRun.getRunningStatus()))
                uniqMapScheduled.put(planRun.getRunningNumber(), planRun);
        }
        ArrayList<PlanRunDto> listUniqScheduled = (ArrayList<PlanRunDto>) uniqMapScheduled.values().
                stream().collect(Collectors.toCollection(ArrayList::new)).stream().
                sorted(Comparator.comparing(PlanRunDto::getRunningDate).reversed()).collect(Collectors.toList());
        List<PlanRunDto> list = new ArrayList<>();
        for (int i = 0; i < uniqMapScheduled.size(); i++) {
            if (!(i == 3)) {
                listUniqScheduled.get(i).setParticipationStatus(listUniqScheduled.get(i).getParticipationStatus() == null ? 0 : 1);
                listUniqScheduled.get(i).setRunnersCount(runnerCountRepository.countRunners(listUniqScheduled.get(i).getTeamsRunningCountId()));
                listUniqScheduled.get(i).setVolunteersCount(volunteerRepository.countVolunteers(listUniqScheduled.get(i).getTeamsRunningCountId()));
                listUniqScheduled.get(i).setRunnerCountId(runnerCountRepository.findIdRunnerCount(currentUserName, listUniqScheduled.get(i).getTeamsRunningCountId()));
                listUniqScheduled.get(i).setStatusVolunteer(volunteerRepository.findStatusVolunteer(currentUserName, listUniqScheduled.get(i).getTeamsRunningCountId()));
                list.add(listUniqScheduled.get(i));
            }
            else break;
        }
        return list;
    }

    // отмена участия в забеге в качестве бегуна
    @Override
    public void deleteRunnerFromRun (Integer runningCountId) {
       runnerCountRepository.deleteById(runningCountId);
    }

    // запись для участия в забеге в качестве бегуна
    @Override
    public void insertRunnerFromRun (String currentUserName, Integer teamsRunningCountId){
        Optional<SecUser> userId = secUserRepository.findByUsername(currentUserName);
        TeamsRunningCount teamsRunningCount = teamsRunningCountRepository.getById(teamsRunningCountId);
        RunnerCount runnerCount = new RunnerCount(null, userId.get(), teamsRunningCount);
        runnerCountRepository.save(runnerCount);
    }
}
