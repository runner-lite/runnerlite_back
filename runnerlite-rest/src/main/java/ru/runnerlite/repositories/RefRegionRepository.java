package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.RefRegion;

@Repository
public interface RefRegionRepository extends JpaRepository<RefRegion, Integer> {
}