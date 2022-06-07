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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<PlanRunDto> findUniqPlanRunUser(String currentUserName) {
        List<PlanRunDto> planRunDto = teamsRunningCountRepository.findPlanRunByUserName(currentUserName);
        List<PlanRunDto> list = new ArrayList<>();
        for (int i = 0; i < planRunDto.size(); i++) {
            if (!(i == 3)) {
                planRunDto.get(i).setParticipationStatus(runnerCountRepository.findStatusRunner(currentUserName, planRunDto.get(i).getTeamsRunningCountId()) == null ? 0 : 1);
                planRunDto.get(i).setRunnersCount(runnerCountRepository.countRunners(planRunDto.get(i).getTeamsRunningCountId()));
                planRunDto.get(i).setVolunteersCount(volunteerRepository.countVolunteers(planRunDto.get(i).getTeamsRunningCountId()));
                planRunDto.get(i).setRunnerCountId(runnerCountRepository.findIdRunnerCount(currentUserName, planRunDto.get(i).getTeamsRunningCountId()));
                planRunDto.get(i).setStatusVolunteer(volunteerRepository.findStatusVolunteer(currentUserName, planRunDto.get(i).getTeamsRunningCountId()));
                planRunDto.get(i).setVolunteersId(volunteerRepository.findVolunteersId(currentUserName, planRunDto.get(i).getTeamsRunningCountId()));
                planRunDto.get(i).setVolunteersPositionId(volunteerRepository.findVolunteersPositionId(currentUserName, planRunDto.get(i).getTeamsRunningCountId()));
                planRunDto.get(i).setPositionName(volunteerRepository.findPositionNameFromRefVolunteersPosition(currentUserName, planRunDto.get(i).getTeamsRunningCountId()));
                list.add(planRunDto.get(i));
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
