package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.runnerlite.entities.dto.SecUserDto;
import ru.runnerlite.services.interfaces.ISecUserService;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/register")
public class RegistrationController {
	
	private final ISecUserService registrationService;

	@Autowired
	public RegistrationController(ISecUserService registrationService) {
		this.registrationService = registrationService;
	}

	@PostMapping
	public ResponseEntity<Integer> registerNewUser(@Valid @RequestBody SecUserDto user) {
		return registrationService.register(user);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String sqlExceptionsHandler(SQLIntegrityConstraintViolationException ex) {
		return ex.getMessage();
	}
}
