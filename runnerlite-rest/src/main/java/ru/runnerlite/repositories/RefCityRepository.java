package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.RefCity;

@Repository
public interface RefCityRepository extends JpaRepository<RefCity, Integer> {
}