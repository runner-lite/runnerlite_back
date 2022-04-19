package ru.runnerlite.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.RefCity;

import java.util.List;

@Repository
public interface RefCityRepository extends CrudRepository<RefCity, Integer> {
	
	List<RefCity> findAll();
}