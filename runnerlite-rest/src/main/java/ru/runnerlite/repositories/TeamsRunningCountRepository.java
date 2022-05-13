package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.TeamsRunningCount;
import ru.runnerlite.entities.dto.PlanRunDto;

import java.util.List;

@Repository
public interface TeamsRunningCountRepository extends JpaRepository<TeamsRunningCount, Integer> {

    @Query(value = "select distinct new ru.runnerlite.entities.dto.PlanRunDto(r.secUser.id, t.status, " +
            "r.teamsRunningCount.runningDate, t.teams.name, t.teams.description," +
            " r.teamsRunningCount.number) " +
            "from TeamsRunningCount t " +
            "join RunnerCount r on r.teamsRunningCount.id = t.id " +
            "where (r.secUser.email=:currentUserName or r.secUser.id is null) and t.status not like 'Выполнен'")
    List<PlanRunDto> findPlanRunByUserName(@Param("currentUserName") String currentUserName);
}