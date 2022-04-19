package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.TeamsVolunteer;

@Repository
public interface TeamsVolunteerRepository extends JpaRepository<TeamsVolunteer, Integer> {
}