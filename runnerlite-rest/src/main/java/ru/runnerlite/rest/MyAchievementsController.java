package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.runnerlite.entities.dto.MyAchievementsDto;
import ru.runnerlite.services.interfaces.IMyAchievementsService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/achievements")
public class MyAchievementsController {

    @Autowired
    IMyAchievementsService myAchievementsService;

    @GetMapping("/get")
    public List<MyAchievementsDto> getMyAchievements(Principal principal) {
        String currentUserName = principal.getName();
        return myAchievementsService.findAchievementsUser(currentUserName);
    }
}
