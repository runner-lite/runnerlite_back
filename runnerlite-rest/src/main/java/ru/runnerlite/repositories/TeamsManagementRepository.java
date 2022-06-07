package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.TeamsManagement;
import ru.runnerlite.entities.dto.TeamsVolunteerPlanningDto;

import java.util.List;

@Repository
public interface TeamsManagementRepository extends JpaRepository<TeamsManagement, Integer> {

    //поиск забега по currentUserName Руководителя забегов
    @Query(value = "select new ru.runnerlite.entities.dto.TeamsVolunteerPlanningDto(trc.id, trc.runningDate, trc.number, trc.status)" +
            "from TeamsManagement tm " +
            "left join TeamsRunningCount trc on trc.teams.id = tm.teams.id " +
            "where tm.secUsers.email=:currentUserName and trc.status not like 'Выполнен' order by trc.runningDate asc")
    List<TeamsVolunteerPlanningDto> findTeamsVolunteerPlanningDto(@Param("currentUserName") String currentUserName);

}