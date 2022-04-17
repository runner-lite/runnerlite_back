package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.RefDistrict;

@Repository
public interface RefDistrictRepository extends JpaRepository<RefDistrict, Integer> {
}