package ru.runnerlite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.RefCity;
import ru.runnerlite.entities.dto.RefCitiesDto;
import ru.runnerlite.repositories.RefCityRepository;
import ru.runnerlite.services.interfaces.ICityService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RefCitiesService implements ICityService {
	
	@Autowired
	RefCityRepository RefCitiesRepository;
	
	@Override
	public List<RefCitiesDto> findAll() {
		List<RefCity> cities = RefCitiesRepository.findAll();
		List<RefCitiesDto> cityDtos = cities.stream()
			.map(c -> new RefCitiesDto(c.getId(), c.getName())).collect(Collectors.toList());
		return cityDtos;
	}
}
