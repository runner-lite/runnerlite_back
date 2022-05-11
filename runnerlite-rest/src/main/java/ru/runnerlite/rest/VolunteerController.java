package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.runnerlite.entities.dto.VolunteerDto;
import ru.runnerlite.services.interfaces.IVolunteerService;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController {

    @Autowired
    IVolunteerService volunteerService;

    @GetMapping("/lastHistory")
    public VolunteerDto getLastHistoryVolunteering(@PathParam("userId") Integer userId) {
        return volunteerService.getLastHistoryVolunteering(userId);
    }

}
