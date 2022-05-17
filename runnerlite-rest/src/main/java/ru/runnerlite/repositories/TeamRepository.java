package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.Team;
import ru.runnerlite.entities.dto.TeamDto;
import ru.runnerlite.entities.dto.TeamWithDistrictDto;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    @Query(value = "select new ru.runnerlite.entities.dto.TeamWithDistrictDto(t.id, t.name, d.name) " +
            "FROM RefCity c " +
            "inner join RefDistrict d on d.refCity = c " +
            "inner join Location l on l.refDistricts = d " +
            "inner join Team t on t.location = l " +
            "where c.id=:id")
    List<TeamWithDistrictDto> findTeamsByCityId(Integer id);

    @Query(value = "select new ru.runnerlite.entities.dto.TeamDto(t.id, t.name, t.description, t.geoLat, t.geoLng, t.geoDescription, t.active) " +
            "FROM Team t " +
            "inner join SecUser s on s.team.id = t.id " +
            "where s.email=:currentUserName")
    TeamDto findMyTeam(@Param("currentUserName") String currentUserName);
}