package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.TeamsRunningCount;
import ru.runnerlite.entities.dto.PlanRunDto;

import java.time.Instant;
import java.util.List;

@Repository
public interface TeamsRunningCountRepository extends JpaRepository<TeamsRunningCount, Integer> {

    @Query(value = "select new ru.runnerlite.entities.dto.PlanRunDto(r.secUser.id, t.status, " +
            "t.runningDate, t.teams.name, t.teams.description, t.number) " +
            "from TeamsRunningCount t " +
            "left join RunnerCount r on r.teamsRunningCount.id = t.id " +
            "left join MailingList m on t.teams.id = m.teams.id " +
            "where m.secUsers.email=:currentUserName and t.status not like 'Выполнен'")
    List<PlanRunDto> findPlanRunByUserName(@Param("currentUserName") String currentUserName);

}