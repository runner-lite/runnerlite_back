package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.TeamsRunningCount;

@Repository
public interface TeamsRunningCountRepository extends JpaRepository<TeamsRunningCount, Integer> {
}