package ru.runnerlite.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.RefCities;

import java.util.List;

@Repository
public interface RefCityRepository extends CrudRepository<RefCities, Integer> {
	
	List<RefCities> findAll();
}