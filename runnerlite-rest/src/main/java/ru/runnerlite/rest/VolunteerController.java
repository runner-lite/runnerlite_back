package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.runnerlite.entities.dto.TeamRunVolunteerQtyDto;
import ru.runnerlite.entities.dto.VolunteerDto;
import ru.runnerlite.services.interfaces.IVolunteerService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController {


    private IVolunteerService volunteerService;


    @Autowired
    public VolunteerController(IVolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @GetMapping("/lastHistory")
    public VolunteerDto getLastHistoryVolunteering(Principal principal) {
        String currentUserName = principal.getName();
        return volunteerService.getLastHistoryVolunteering(currentUserName);
    }

    //Получение шаблона команды для волонтерсва
    @PreAuthorize("@A.mayUserChangeThisTeam(principal,#teamId)")
    @GetMapping("/template")
    public List<TeamRunVolunteerQtyDto> getNeedTeamRunVolunteerQty(@RequestParam("teamId") Integer teamId){
        return volunteerService.getNeedTeamRunVolunteerQty(teamId);
    }

    //Добавление новое записи в шаблон волонтерства
    @PreAuthorize("hasAuthority('ROLE_Admin')&&@A.mayUserChangeThisTeam(principal,#teamId)") // проверка на роль && проверка на включение в ту же команду
    @PutMapping("/template")
    public List<TeamRunVolunteerQtyDto> putNeedTeamRunVolunteerQty(@RequestParam("teamId") Integer teamId,
    @Valid @RequestBody TeamRunVolunteerQtyDto teamRunVolunteerQtyDto){
        volunteerService.putNeedTeamRunVolunteerQty(teamRunVolunteerQtyDto);
        return volunteerService.getNeedTeamRunVolunteerQty(teamId);
    }

}
