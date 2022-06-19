package ru.runnerlite.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.runnerlite.entities.RunningResult;
import ru.runnerlite.entities.SecUser;
import ru.runnerlite.entities.TeamsRunningCount;
import ru.runnerlite.entities.dto.*;

import java.util.List;
import java.util.Optional;

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

	//поиск результата забега для обновления данных
	@Query(value = "select rr from RunningResult rr where rr.secUser.id =:userId and rr.teamsRunningCount.id =:teamsRunningCountId")
	Optional<RunningResult> findRunningResult(@Param("userId") Integer userId, @Param("teamsRunningCountId") Integer teamsRunningCountId);

	//изменение результата забега
	@Transactional
	@Modifying
    @Query(value = "update RunningResult rr set rr.secUser = :user, rr.result =:result, rr.teamsRunningCount =:teamsRunningCount" +
            " where rr.secUser.id=:userId and rr.teamsRunningCount.id =:teamsRunningCountId")
    void changeRunningResult(@Param("userId") Integer userId, @Param("teamsRunningCountId") Integer teamsRunningCountId,@Param("user") SecUser user, @Param("result") Integer result, @Param("teamsRunningCount") TeamsRunningCount teamsRunningCount);

	//список внесенных результатов забега
	@Query(value = "select new ru.runnerlite.entities.dto.RunningResultForChangeTableDto(rr.id, rr.secUser.id, rr.secUser.fullName, rr.secUser.nickName, rr.result, rr.teamsRunningCount.id, rr.finishPlace, rr.secUser.useNick)" +
			" from RunningResult rr where rr.teamsRunningCount.id =:teamsRunningCountId and rr.finishPlace = null ORDER BY  rr.result ")
    List<RunningResultForChangeTableDto> findListRunningResult(@Param("teamsRunningCountId") Integer teamsRunningCountId);

	@Query(value = "select r from RunningResult r where r.teamsRunningCount.id =:teamsRunningCountId")
	List<RunningResult> findRunningResultByTeamRunningCountId(@Param("teamsRunningCountId") Integer teamsRunningCountId);
}