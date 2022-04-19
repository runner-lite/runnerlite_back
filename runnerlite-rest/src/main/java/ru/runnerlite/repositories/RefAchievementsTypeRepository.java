package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.RefAchievementsType;

@Repository
public interface RefAchievementsTypeRepository extends JpaRepository<RefAchievementsType, Integer> {
}