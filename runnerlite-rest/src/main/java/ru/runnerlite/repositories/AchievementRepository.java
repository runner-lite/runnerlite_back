package ru.runnerlite.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.Achievement;

@Repository
public interface AchievementRepository extends CrudRepository<Achievement, Long> {
}