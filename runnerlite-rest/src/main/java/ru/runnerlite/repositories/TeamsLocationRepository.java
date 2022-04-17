package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.TeamsLocation;

@Repository
public interface TeamsLocationRepository extends JpaRepository<TeamsLocation, Integer> {
}