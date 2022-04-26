package ru.runnerlite.services.interfaces;

import ru.runnerlite.entities.dto.TeamDto;

import java.util.List;

public interface ITeamService {
	List<TeamDto> findAllByCityId(Integer id);
}
