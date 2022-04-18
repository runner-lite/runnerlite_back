package ru.runnerlite.services;

import org.springframework.http.ResponseEntity;
import ru.runnerlite.entities.dto.RefCitiesDto;

import java.util.List;

public interface ICityService {
	List<RefCitiesDto> findAll();
}
