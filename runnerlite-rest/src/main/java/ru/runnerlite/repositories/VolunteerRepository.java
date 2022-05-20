package ru.runnerlite.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.Volunteer;
import ru.runnerlite.entities.dto.TeamRunVolunteerQtyDto;
import ru.runnerlite.entities.dto.VolunteerDto;

import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {

   @Query(value = "select new ru.runnerlite.entities.dto.VolunteerDto(v.id, v.secUsers.id, v.teamsRunningCount.runningDate, " +
           "t.number, v.refVolunteersPosition.name, v.refVolunteersPosition.description, " +
           "t.teams.name, t.teams.id) " +
           "from Volunteer v " +
           "left join TeamsRunningCount t on t.id = v.teamsRunningCount.id " +
           "where v.secUsers.email=:currentUserName and t.status like 'Выполнен' order by t.runningDate desc")
   List<VolunteerDto> findVolunteerByUserName(@Param("currentUserName") String currentUserName, Pageable pageable);

   @Query("select v.refVolunteersPosition.name from Volunteer v " +
           "left join TeamsRunningCount t on t.id = v.teamsRunningCount.id " +
           "where v.secUsers.email=:currentUserName and t.status like 'Выполнен' ")
   List<String> historicalListVolunteerism (@Param("currentUserName") String currentUserName); //список позиций в которых участвовал волонтером

   @Query("select count (v.secUsers) from Volunteer v " +
           "left join TeamsRunningCount t on t.id = v.teamsRunningCount.id " +
           "where v.secUsers.email=:currentUserName and t.status like 'Выполнен' ")
   Long historicalVolunteerismCount (@Param("currentUserName") String currentUserName); // колличество участий в роли волонтера

   @Query("select count (v.secUsers) from Volunteer v " +
           "left join TeamsRunningCount t on t.id = v.teamsRunningCount.id " +
           "where v.teamsRunningCount.id=:teamsRunningCountId")
   Integer countVolunteers(Integer teamsRunningCountId); //количество волонтеров участвующих в забеге

   @Query(value = "select new ru.runnerlite.entities.dto.VolunteerDto(v.id, v.secUsers.id, v.secUsers.fullName, v.status, " +
           "v.refVolunteersPosition.name, v.refVolunteersPosition.description) " +
           "from Volunteer v " +
           "left join SecUser u on u.id = v.secUsers.id " +
           "where v.teamsRunningCount.id=:teamsRunningCountId")
   List<VolunteerDto> findVolunteerByTeamsRunningCountId(@Param("teamsRunningCountId") Integer teamsRunningCountId); //поиск волонтеров по номеру забега

   @Query(value = "select new ru.runnerlite.entities.dto.TeamRunVolunteerQtyDto(" +
           "t.team.id," +
           "rvp.id," +
           "rvp.name," +
           "t.qty) " +
           "from TeamsVolunteerTemplate t " +
           "left join RefVolunteersPosition rvp on rvp.id = t.refVolunteersPosition.id " +
           "where t.team.id=:teamId")
   List<TeamRunVolunteerQtyDto> getNeedTeamRunVolunteerQty(@Param("teamId") Integer teamId); //поиск шаблона волонтерства по ид команды

}