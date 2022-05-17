package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.runnerlite.entities.dto.TeamDto;
import ru.runnerlite.entities.dto.TeamWithDistrictDto;
import ru.runnerlite.services.interfaces.ITeamService;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {
	
	@Autowired
	ITeamService teamsService;
	
	@GetMapping("/filter")
	public List<TeamWithDistrictDto> getTeamsByCityId(@PathParam("city") Integer city) {
		return teamsService.findByCid(city);
	}

	@GetMapping("/my")
	public TeamDto getMyTeam(Principal principal) {
		String currentUserName = principal.getName();
		return teamsService.getMyTeam(currentUserName);
	}
	/*
	public List<TeamDto> getTeamsByCityId(@PathParam("city") Integer city) {
		return teamsService.findAllByCityId(city);
	}

	 */
}
