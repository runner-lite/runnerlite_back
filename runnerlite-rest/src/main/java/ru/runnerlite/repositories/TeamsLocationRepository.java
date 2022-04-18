package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.Location;

@Repository
public interface TeamsLocationRepository extends JpaRepository<Location, Integer> {
}