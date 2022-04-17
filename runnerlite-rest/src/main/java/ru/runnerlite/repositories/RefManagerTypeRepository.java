package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.RefManagerType;

@Repository
public interface RefManagerTypeRepository extends JpaRepository<RefManagerType, Integer> {
}