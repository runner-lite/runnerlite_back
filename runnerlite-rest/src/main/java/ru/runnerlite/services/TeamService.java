package ru.runnerlite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.Team;
import ru.runnerlite.repositories.TeamRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService implements ITeamService {
	
	@Autowired
	TeamRepository teamRepository;
	
	public List<Team> findAllByCityId(Integer id) {
		List<Team> allTeams = teamRepository.findAll();
		
		return allTeams.stream()
			.filter(t -> id.equals(t.getLocation().getRefDistricts().getRefCity().getId()))
			.collect(Collectors.toList());
	}
}
