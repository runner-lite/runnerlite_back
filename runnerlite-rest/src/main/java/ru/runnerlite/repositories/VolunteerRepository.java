package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.Volunteer;
import ru.runnerlite.entities.dto.VolunteerDto;

import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {

   @Query(value = "select new ru.runnerlite.entities.dto.VolunteerDto(v.id, v.secUsers.id, v.teamsRunningCount.runningDate, " +
           "v.teamsRunningCount.number, v.refVolunteersPosition.name, v.refVolunteersPosition.description, " +
           "v.teamsRunningCount.teams.name, v.teamsRunningCount.teams.id, count (v.secUsers)) " +
           "from Volunteer v " +
           "where v.secUsers.email=:currentUserName")
   VolunteerDto findVolunteerByUserId(@Param("currentUserName") String currentUserName);

   @Query("select v.refVolunteersPosition.name from Volunteer v where v.secUsers.email=:currentUserName")
   List<String> historicalistVolunteerism (@Param("currentUserName") String currentUserName);
}