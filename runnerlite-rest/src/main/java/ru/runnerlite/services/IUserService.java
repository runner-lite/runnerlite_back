package ru.runnerlite.services;

import org.springframework.http.ResponseEntity;
import ru.runnerlite.entities.dto.SecUserDto;

public interface IUserService {
	
	ResponseEntity<SecUserDto> register(SecUserDto user);
	
	ResponseEntity<SecUserDto> getById(Integer id);
}
