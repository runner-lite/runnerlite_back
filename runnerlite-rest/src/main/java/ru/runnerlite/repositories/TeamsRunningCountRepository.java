package ru.runnerlite.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.TeamsRunningCount;
import ru.runnerlite.entities.dto.PlanRunDto;
import ru.runnerlite.entities.dto.TeamsRunningCountDto;

import java.time.Instant;
import java.util.List;

@Repository
public interface TeamsRunningCountRepository extends JpaRepository<TeamsRunningCount, Integer> {

    @Query(value = "select new ru.runnerlite.entities.dto.PlanRunDto(t.id, t.status, " +
            "t.runningDate, t.teams.name, t.teams.description, t.number) " +
            "from TeamsRunningCount t " +
            "left join SecUser su on su.team.id = t.teams.id " +
            "where su.email=:currentUserName and t.status not like 'Выполнен' Order by t.runningDate asc")
    List<PlanRunDto> findPlanRunByUserName(@Param("currentUserName") String currentUserName);
    
    List<TeamsRunningCount> findByTeamsIdOrderByIdAsc(Integer teamId);
    List<TeamsRunningCount> findByTeamsIdAndRunningDateAfterOrderByIdAsc(Integer teamId, Instant dateFrom);

    @Query(value = "select new ru.runnerlite.entities.dto.TeamsRunningCountDto(t) " +
            "from TeamsRunningCount t " +
            "where t.teams.id =:teamId " +
            "ORDER BY  t.runningDate desc")
    List<TeamsRunningCountDto> getTeamRunningResults(@Param("teamId") Integer teamId);
  
    @Query(value = "select new ru.runnerlite.entities.dto.TeamsRunningCountDto(t) " +
            "from TeamsRunningCount t " +
            "where t.teams.id =:teamId " +
            "ORDER BY  t.runningDate desc ")
    List<TeamsRunningCountDto> getTeamRunningResults(@Param("teamId") Integer teamId, Pageable pageable);

    @Query(value = "select t from TeamsRunningCount t where t.teams.id =:teamId and t.number =:runningId")
    TeamsRunningCount findTeamsRunningCountByIdAndTeamId(@Param("teamId") Integer teamId,@Param("runningId") Integer runningId);
  
    @Query("select trc.runningDate " +
            "from TeamsRunningCount trc " +
            "where trc.number=:teamRunning")
    Instant getRunningDate(@Param("teamRunning") Integer teamRunning);
}
