package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.RefVolunteersPosition;

@Repository
public interface RefVolunteersPositionRepository extends JpaRepository<RefVolunteersPosition, Integer> {
}