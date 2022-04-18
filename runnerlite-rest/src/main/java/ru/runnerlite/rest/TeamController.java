package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.runnerlite.entities.Team;
import ru.runnerlite.services.ITeamService;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {
	
	@Autowired
	ITeamService teamsService;
	
	@GetMapping("/getByCityId/{id}/id")
	public List<Team> getAllTeams(@PathVariable("id") Integer id) {
		return teamsService.findAllByCityId(id);
	}
}
