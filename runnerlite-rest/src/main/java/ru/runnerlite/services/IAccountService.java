package ru.runnerlite.services;

import org.springframework.http.ResponseEntity;
import ru.runnerlite.entities.dto.Account;
import ru.runnerlite.entities.dto.SecUserDto;

public interface IAccountService {
	
	ResponseEntity<Account> register(SecUserDto user);
	
	ResponseEntity<Account> get(Integer id);
}
