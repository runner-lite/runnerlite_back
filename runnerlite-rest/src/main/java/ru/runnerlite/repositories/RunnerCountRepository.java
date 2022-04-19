package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.RunnerCount;

@Repository
public interface RunnerCountRepository extends JpaRepository<RunnerCount, Integer> {
}