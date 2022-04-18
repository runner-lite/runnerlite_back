package ru.runnerlite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.Team;
import ru.runnerlite.repositories.TeamRepository;

import java.util.List;

@Service
public class TeamService implements ITeamService {
	
	@Autowired
	TeamRepository teamRepository;
	
	public List<Team> findAllByCityId(Integer id) {
		return teamRepository.findAll();
	}
}
