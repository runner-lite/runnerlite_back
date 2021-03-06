package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.TeamsVolunteer;
import ru.runnerlite.entities.dto.PlanVolunteerDto;
import ru.runnerlite.entities.dto.TeamsVolunteerDto;

import java.util.List;

@Repository
public interface TeamsVolunteerRepository extends JpaRepository<TeamsVolunteer, Integer> {

    @Query(value = "select new ru.runnerlite.entities.dto.PlanVolunteerDto(t.id, t.runningDate, rvp.id, t.number, rvp.name, rvp.description, tv.needVolunteerQty)" +
            "from TeamsVolunteer tv " +
            "left join TeamsRunningCount t on t.id = tv.teamsRunningCount.id " +
            "left join RefVolunteersPosition rvp on rvp.id = tv.refVolunteersPosition.id " +
            "where t.id=:teamsRunningCountId and t.status not like 'Выполнен' order by rvp.name asc")
    List<PlanVolunteerDto> findPlanVolunteer(@Param("teamsRunningCountId") Integer teamsRunningCountId);

    //информация о необходимом кол-ве волонтеров на забег
    @Query(value = "select new ru.runnerlite.entities.dto.TeamsVolunteerDto(tv.id, tv.refVolunteersPosition.id, tv.refVolunteersPosition.name, tv.refVolunteersPosition.description, tv.needVolunteerQty)" +
            "from TeamsVolunteer tv " +
            "where tv.teamsRunningCount.id=:teamsRunningCountId")
    List<TeamsVolunteerDto> findTeamVolunteerDto(@Param("teamsRunningCountId") Integer teamsRunningCountId);


	
	List<TeamsVolunteer> findByTeamsRunningCountId(Integer runningId);
	
	@Query(value = "SELECT NEED_VOLUNTEER_QTY FROM teams_volunteers WHERE TEAMS_RUNNING_COUNT_ID = ?0", nativeQuery = true)
	Integer getNeedVolunteersCount(Integer runningId);
}