package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.runnerlite.entities.RunnerCount;
import ru.runnerlite.entities.SecUser;
import ru.runnerlite.entities.TeamsRunningCount;
import ru.runnerlite.entities.dto.PlanRunDto;
import ru.runnerlite.repositories.RunnerCountRepository;
import ru.runnerlite.repositories.SecUserRepository;
import ru.runnerlite.repositories.TeamsRunningCountRepository;
import ru.runnerlite.services.interfaces.IPlanRunService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/planRun")
public class PlanRunController {

    private IPlanRunService planRunService;
    private RunnerCountRepository runnerCountRepository;
    private SecUserRepository secUserRepository;
    private TeamsRunningCountRepository teamsRunningCountRepository;

    @Autowired
    public PlanRunController(IPlanRunService planRunService, RunnerCountRepository runnerCountRepository, SecUserRepository secUserRepository, TeamsRunningCountRepository teamsRunningCountRepository) {
        this.planRunService = planRunService;
        this.runnerCountRepository = runnerCountRepository;
        this.secUserRepository = secUserRepository;
        this.teamsRunningCountRepository = teamsRunningCountRepository;
    }

    @GetMapping("/get")
    public List<PlanRunDto> getPlanRunDto(Principal principal) {
        String currentUserName = principal.getName();
        return planRunService.findUniqPlanRunUser(currentUserName);
    }

    @GetMapping("/deleteRunnerCount")
    public String deleteRunnerCount(Integer runningCountId){
        Optional<RunnerCount> runnerCount = runnerCountRepository.findById(runningCountId);
        runnerCountRepository.delete(runnerCount.get());
        return "success";
    }

    @GetMapping("/insertRunnerCount")
    public String insertRunnerCount(Principal principal, Integer teamsRunningCountId){
        Optional<SecUser> userId = secUserRepository.findByUsername(principal.getName());
        TeamsRunningCount teamsRunningCount = teamsRunningCountRepository.getById(teamsRunningCountId);
        RunnerCount runnerCount = new RunnerCount(null, userId.get(), teamsRunningCount);
        runnerCountRepository.save(runnerCount);
        return "success";
    }
}
