package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.runnerlite.entities.dto.Account;
import ru.runnerlite.services.interfaces.IAccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	IAccountService accountService;
	
	@GetMapping
	public ResponseEntity<Account> get(@RequestParam("id") Integer id) {
		return accountService.get(id);
	}
}