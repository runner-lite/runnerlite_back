package ru.runnerlite.services.interfaces;

import ru.runnerlite.entities.dto.TeamDto;
import ru.runnerlite.entities.dto.TeamWithDistrictDto;

import java.util.List;

public interface ITeamService {

	List<TeamWithDistrictDto> findByCid(Integer id);
	TeamDto getMyTeam (String currentUserName);
}
