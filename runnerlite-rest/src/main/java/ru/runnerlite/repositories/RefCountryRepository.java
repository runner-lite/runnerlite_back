package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.RefCountry;

@Repository
public interface RefCountryRepository extends JpaRepository<RefCountry, Integer> {
}