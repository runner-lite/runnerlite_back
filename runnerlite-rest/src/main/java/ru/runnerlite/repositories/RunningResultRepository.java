package ru.runnerlite.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.RunningResult;
import ru.runnerlite.entities.dto.RunningResultDto;
import ru.runnerlite.entities.dto.RunningResultForEmailSendDto;
import ru.runnerlite.entities.dto.TourneyTableDto;

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

	@Query(value = "select new ru.runnerlite.entities.dto.TourneyTableDto(r.finishPlace, r.secUser.id, " +
			"r.secUser.fullName, r.secUser.nickName, r.result)" +
			"from RunningResult r " +
			"where r.teamsRunningCount.number=:teamRunning ORDER BY r.finishPlace asc")
    List<TourneyTableDto> findAllResultByTeamRunning(@Param("teamRunning") Integer teamRunning); // таблица результатов забега

	@Query(value="select new ru.runnerlite.entities.dto.RunningResultForEmailSendDto(r.secUser.email, " +
			"r.secUser.fullName, " +
			"r.teamsRunningCount.number, " +
			"r.teamsRunningCount.runningDate, " +
			"r.teamsRunningCount.teams.name, " +
			"r.finishPlace, " +
			"r.result) " +
			" from RunningResult r where r.teamsRunningCount.number=:teamRunning and r.teamsRunningCount.teams.id=:teamId")
	List<RunningResultForEmailSendDto> findAllResultByTeamRunningId(@Param("teamRunning") Integer teamRunning, @Param("teamId") Integer teamId);

}