package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.TeamsVolunteer;

import java.util.List;

@Repository
public interface TeamsVolunteerRepository extends JpaRepository<TeamsVolunteer, Integer> {
	
	List<TeamsVolunteer> findByTeamsRunningCountId(Integer runningId);
	
	@Query(value = "SELECT NEED_VOLUNTEER_QTY FROM teams_volunteers WHERE TEAMS_RUNNING_COUNT_ID = ?0", nativeQuery = true)
	Integer getNeedVolunteersCount(Integer runningId);
}