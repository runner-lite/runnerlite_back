package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.runnerlite.entities.dto.SecUserDto;
import ru.runnerlite.services.interfaces.ISecUserService;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	ISecUserService registrationService;
	
	@GetMapping("/find")
	public ResponseEntity<SecUserDto> findUser(@RequestParam(name = "id") Integer id) {
		
		return registrationService.getById(id);
	}
}