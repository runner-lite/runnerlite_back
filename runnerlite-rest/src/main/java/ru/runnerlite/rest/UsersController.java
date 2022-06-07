package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.runnerlite.entities.dto.SecUserDto;
import ru.runnerlite.entities.dto.UserNameDto;
import ru.runnerlite.services.interfaces.ISecUserService;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	ISecUserService registrationService;
	
	@GetMapping("/find")
	public ResponseEntity<SecUserDto> findUser(@RequestParam(name = "id") Integer id) {
		
		return registrationService.getById(id);
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/findUser")
	public UserNameDto findByEmail(Principal principal) {
		String currentUserName = principal.getName();
		return registrationService.findByEmail(currentUserName);
	}
}