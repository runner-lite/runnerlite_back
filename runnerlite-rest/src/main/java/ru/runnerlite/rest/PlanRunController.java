package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.runnerlite.entities.dto.MyAchievementsDto;
import ru.runnerlite.entities.dto.PlanRunDto;
import ru.runnerlite.services.interfaces.IMyAchievementsService;
import ru.runnerlite.services.interfaces.IPlanRunService;

import java.security.Principal;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/planrun")
public class PlanRunController {

    @Autowired
    IPlanRunService planRunService;

    @GetMapping("/get")
    public List<PlanRunDto> getPlanRunDto(Principal principal) {
        String currentUserName = principal.getName();
        return planRunService.findUniqPlanRunUser(currentUserName);
    }

}
