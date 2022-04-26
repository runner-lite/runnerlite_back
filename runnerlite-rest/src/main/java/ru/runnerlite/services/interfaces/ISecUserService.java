package ru.runnerlite.services.interfaces;

import org.springframework.http.ResponseEntity;
import ru.runnerlite.entities.dto.SecUserDto;

public interface ISecUserService {
	
	ResponseEntity<SecUserDto> register(SecUserDto user);
	
	ResponseEntity<SecUserDto> getById(Integer id);
}
