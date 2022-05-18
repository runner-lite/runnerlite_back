package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.TeamsVolunteer;
import ru.runnerlite.entities.dto.PlanRunDto;
import ru.runnerlite.entities.dto.PlanVolunteerDto;

import java.util.List;

@Repository
public interface TeamsVolunteerRepository extends JpaRepository<TeamsVolunteer, Integer> {

    @Query(value = "select new ru.runnerlite.entities.dto.PlanVolunteerDto(t.id, t.runningDate, rvp.id, t.number, rvp.name, rvp.description, tv.needVolunteerQty)" +
            "from TeamsVolunteer tv " +
            "left join TeamsRunningCount t on t.id = tv.teamsRunningCount.id " +
            "left join RefVolunteersPosition rvp on rvp.id = tv.refVolunteersPosition.id " +
            "where t.id=:teamsRunningCountId and t.status not like 'Выполнен'")
    List<PlanVolunteerDto> findPlanVolunteer(@Param("teamsRunningCountId") Integer teamsRunningCountId);


}