package ru.runnerlite.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.RunningResult;
import ru.runnerlite.entities.dto.RunningResultDto;

import java.util.List;

@Repository
public interface RunningResultRepository extends JpaRepository<RunningResult, Integer> {

	@Query(value = "select new ru.runnerlite.entities.dto.RunningResultDto(r.id, r.teamsRunningCount.id, r.secUser.id, " +
			"r.finishPlace, r.result, r.teamsRunningCount.runningDate, r.teamsRunningCount.number, r.teamsRunningCount.teams.name, " +
			"r.teamsRunningCount.teams.description, r.teamsRunningCount.teams.geoDescription, r.teamsRunningCount.teams.id)" +
			"from RunningResult r")
	List<RunningResultDto> findAllResult();

	@Query(value = "select new ru.runnerlite.entities.dto.RunningResultDto(r.id, r.teamsRunningCount.id, r.secUser.id, " +
			"r.finishPlace, r.result, r.teamsRunningCount.runningDate, r.teamsRunningCount.number, r.teamsRunningCount.teams.name, " +
			"r.teamsRunningCount.teams.description, r.teamsRunningCount.teams.geoDescription, r.teamsRunningCount.teams.id) " +
			"from RunningResult r " +
			"left join TeamsRunningCount t on t.id = r.teamsRunningCount.id " +
			"where r.secUser.email=:currentUserName ORDER BY t.runningDate desc")
	List<RunningResultDto> findLastResult(String currentUserName, Pageable pageable);
}