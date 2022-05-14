package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.Achievement;
import ru.runnerlite.entities.dto.MyAchievementsDto;
import ru.runnerlite.entities.dto.VolunteerDto;

import java.util.List;

@Repository
public interface AchievementRepository extends CrudRepository<Achievement, Long> {



    @Query(value = "select new ru.runnerlite.entities.dto.MyAchievementsDto(" +
            "a.runningResults.teamsRunningCount.number, " +
            "a.runningResults.teamsRunningCount.runningDate, " +
            "a.runningResults.teamsRunningCount.teams.name, " +
            "a.runningResults.teamsRunningCount.teams.id," +
            "a.refAchievementsType.description, " +
            "a.refAchievementsType.id, " +
            "a.runningResults.finishPlace, " +
            "a.runningResults.result) " +
            "from Achievement a " +
            "where a.runningResults.secUser.email=:currentUserName")
    List<MyAchievementsDto> findAchievementByUserName(@Param("currentUserName") String currentUserName);
}