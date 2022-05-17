package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.RunnerCount;

@Repository
public interface RunnerCountRepository extends JpaRepository<RunnerCount, Integer> {

    @Query("select count (r.secUser) from RunnerCount r " +
            "left join TeamsRunningCount t on t.id = r.teamsRunningCount.id " +
            "where r.teamsRunningCount.id=:teamsRunningCountId")
    Integer countRunners(Integer teamsRunningCountId); //количество бегунов участвующих в забеге

    @Query("select r.id from RunnerCount r " +
            "where r.secUser.email =:currentUserName and r.teamsRunningCount.id=:teamsRunningCountId")
    Integer findIdRunnerCount (@Param("currentUserName") String currentUserName, @Param("teamsRunningCountId")Integer teamsRunningCountId);
}