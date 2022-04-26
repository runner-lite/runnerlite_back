package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.RunningResult;

@Repository
public interface RunningResultRepository extends JpaRepository<RunningResult, Integer> {
	RunningResult findFirstBySecUser_IdOrderByIdDesc(Integer userId);
}