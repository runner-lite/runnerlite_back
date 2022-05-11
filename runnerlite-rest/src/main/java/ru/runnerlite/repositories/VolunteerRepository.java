package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.Volunteer;

import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {
   Volunteer findFirstBySecUsers_IdOrderByIdDesc(Integer userId);

   Integer countBySecUsers_Id(Integer userId);

   @Query("select v.refVolunteersPosition.name from Volunteer v where v.secUsers.id = :userId")
   List<String> findVolunteerPositionName (@Param("userId") Integer userId);
}