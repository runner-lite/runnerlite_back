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
import java.util.Optional;

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
            "where trc.id=:teamRunningId and trc.status like 'Выполнен'")
    Instant getRunningDate(@Param("teamRunningId") Integer teamRunningId);

    @Query("select trc.number " +
            "from TeamsRunningCount trc " +
            "where trc.id=:teamRunningId and trc.status like 'Выполнен'")
    Integer getRunningNumber(@Param("teamRunningId") Integer teamRunningId);

    //поиск забега для внесения изменений в таблицу результатов
    @Query(value = "select trc from TeamsRunningCount trc where trc.teams.id =:teamId and trc.runningDate =:date and trc.status like 'Запланирован'")
    Optional<TeamsRunningCountDto> findTeamsRunningCountByRunningDate(@Param("date") Instant date, @Param("teamId") Integer teamId);

    //изменение руководителем забегов статуса забега с "запланирован" на "выполнен", (закрытие турнирной таблицы)
//    @Transactional
//    @Modifying
//    @Query(value = "update TeamsRunningCount trc set trc.status = 'Выполнен'" +
//            " where trc.number=:runningNumber and trc.teams.id =:teamId")
//    void changeTeamsRunningCountStatus(@Param("teamId") Integer teamId, @Param("runningNumber") Integer runningNumber);
    
    @Query("select max(trc.number) from TeamsRunningCount trc where trc.teams.id = :teamId")
    Optional<Integer> getNewRunningNumber(@Param("teamId") Integer teamId);
    
}
