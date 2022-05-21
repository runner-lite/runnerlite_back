package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.runnerlite.entities.TeamsVolunteerTemplate;

public interface TeamsVolunteerTemplateRepository extends JpaRepository<TeamsVolunteerTemplate, Integer> {

    @Query(value = "select t " +
            "from TeamsVolunteerTemplate t " +
            "where t.team.id=:teamId " +
            "and t.refVolunteersPosition.id=:positionId")
    TeamsVolunteerTemplate findTemplateInTeamByPosition(@Param("teamId") Integer teamId,@Param("positionId") Integer positionId);

}