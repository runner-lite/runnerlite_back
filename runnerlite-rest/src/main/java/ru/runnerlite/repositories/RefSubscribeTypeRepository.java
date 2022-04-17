package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.RefSubscribeType;

@Repository
public interface RefSubscribeTypeRepository extends JpaRepository<RefSubscribeType, Integer> {
}