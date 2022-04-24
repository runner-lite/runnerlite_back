package ru.runnerlite.services;

import ru.runnerlite.entities.dto.RefCitiesDto;

import java.util.List;

public interface ICityService {
	List<RefCitiesDto> findAll();
}
