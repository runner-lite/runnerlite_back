package ru.runnerlite.services;

import ru.runnerlite.entities.Team;

import java.util.List;

public interface ITeamService {
	List<Team> findAllByCityId(Integer id);
}
