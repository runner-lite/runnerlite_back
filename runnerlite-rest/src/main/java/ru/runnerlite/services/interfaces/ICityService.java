package ru.runnerlite.services.interfaces;

import ru.runnerlite.entities.dto.RefCitiesDto;

import java.util.List;

public interface ICityService {
	List<RefCitiesDto> findAll();
}
