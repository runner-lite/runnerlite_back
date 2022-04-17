package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.SecGroup;

@Repository
public interface SecGroupRepository extends JpaRepository<SecGroup, Integer> {
}