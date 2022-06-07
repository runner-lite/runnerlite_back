package ru.runnerlite.services.interfaces;

import org.springframework.http.ResponseEntity;
import ru.runnerlite.entities.dto.SecUserDto;
import ru.runnerlite.entities.dto.UserNameDto;

public interface ISecUserService {
	
	ResponseEntity<Integer> register(SecUserDto user);
	
	ResponseEntity<SecUserDto> getById(Integer id);

	UserNameDto findByEmail(String email);
}
