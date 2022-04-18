package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
	
//	@Query("select t from teams t " +
//		"left join teams_locations tl on tl.teams_id = t.id " +
//		"left join ref_districts ds on ds.id = tl.ref_districts_id " +
//		"where ds.ref_cities_id = :id")
//	List<Team> findAllByCityId(Integer id);
}