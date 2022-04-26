package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.runnerlite.entities.dto.SecUserDto;
import ru.runnerlite.services.interfaces.ISecUserService;

@RestController
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	ISecUserService registrationService;
	
	@PostMapping
	public ResponseEntity<SecUserDto> registerNewUser(@RequestBody SecUserDto user) {
		
		return registrationService.register(user);
	}
}
