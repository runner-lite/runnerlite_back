package ru.runnerlite.services;

import org.springframework.stereotype.Service;
import ru.runnerlite.entities.Team;
import ru.runnerlite.entities.dto.TeamDto;
import ru.runnerlite.entities.dto.TeamWithDistrictDto;
import ru.runnerlite.repositories.SecUserRepository;
import ru.runnerlite.repositories.TeamRepository;
import ru.runnerlite.services.interfaces.ITeamService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService implements ITeamService {
	
	private final TeamRepository teamRepository;
	private final SecUserRepository userRepository;
	
	public TeamService(TeamRepository teamRepository, SecUserRepository userRepository) {
		this.teamRepository = teamRepository;
		this.userRepository = userRepository;
	}
	
	public List<TeamDto> findAllByCityId(Integer id) {
		List<Team> allTeams = teamRepository.findAll();
		
		return allTeams.stream()
			.filter(t -> id.equals(t.getLocation().getRefDistricts().getRefCity().getId()))
			.map(this::convert)
			.collect(Collectors.toList());
	}
	
	TeamDto convert(Team team) {
		TeamDto teamDto = new TeamDto();
		teamDto.setId(team.getId());
		teamDto.setName(team.getName());
		teamDto.setDescription(team.getDescription());
		teamDto.setGeoLat(team.getGeoLat());
		teamDto.setGeoLng(team.getGeoLng());
		teamDto.setGeoDescription(team.getGeoDescription());
		teamDto.setActive(team.getActive());
		return teamDto;
	}
	
	Team convert(TeamDto teamDto) {
		Team team = new Team();
		team.setId(teamDto.getId());
		team.setName(teamDto.getName());
		team.setDescription(teamDto.getDescription());
		team.setGeoLat(teamDto.getGeoLat());
		team.setGeoLng(teamDto.getGeoLng());
		team.setGeoDescription(teamDto.getGeoDescription());
		team.setActive(teamDto.getActive());
		return team;
	}

	public List<TeamWithDistrictDto> findByCid(Integer id) {
		return teamRepository.findTeamsByCityId(id);
	}

	@Override
	public TeamDto getMyTeam(String currentUserName) {
		return teamRepository.findMyTeam(currentUserName);
	}
}
