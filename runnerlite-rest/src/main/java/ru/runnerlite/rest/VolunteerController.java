package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.runnerlite.entities.dto.TeamRunVolunteerQtyDto;
import ru.runnerlite.entities.dto.VolunteerDto;
import ru.runnerlite.repositories.SecUserRepository;
import ru.runnerlite.services.interfaces.IVolunteerService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController {


    private IVolunteerService volunteerService;
    private final SecUserRepository secUserRepository;


    @Autowired
    public VolunteerController(IVolunteerService volunteerService, SecUserRepository secUserRepository) {
        this.volunteerService = volunteerService;
        this.secUserRepository = secUserRepository;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/lastHistory")
    public VolunteerDto getLastHistoryVolunteering(Principal principal) {
        String currentUserName = principal.getName();
        return volunteerService.getLastHistoryVolunteering(currentUserName);
    }

    //Получение шаблона команды для волонтерсва
    @PreAuthorize("hasAuthority('ROLE_Admin')")
    @GetMapping("/template")
    public List<TeamRunVolunteerQtyDto> getNeedTeamRunVolunteerQty(Principal principal){
        String userName = principal.getName();
        Integer teamId = secUserRepository.findTeamByUsername(userName).orElse(-1);
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
