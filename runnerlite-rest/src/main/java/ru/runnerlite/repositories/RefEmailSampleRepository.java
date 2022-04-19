package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.RefEmailSample;

@Repository
public interface RefEmailSampleRepository extends JpaRepository<RefEmailSample, Integer> {
}