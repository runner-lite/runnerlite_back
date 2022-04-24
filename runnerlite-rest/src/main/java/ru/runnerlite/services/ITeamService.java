package ru.runnerlite.services;

import ru.runnerlite.entities.dto.TeamDto;

import java.util.List;

public interface ITeamService {
	List<TeamDto> findAllByCityId(Integer id);
}
