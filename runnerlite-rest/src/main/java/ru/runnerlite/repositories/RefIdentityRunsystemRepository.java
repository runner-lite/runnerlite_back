package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.RefIdentityRunsystem;

@Repository
public interface RefIdentityRunsystemRepository extends JpaRepository<RefIdentityRunsystem, Integer> {
}