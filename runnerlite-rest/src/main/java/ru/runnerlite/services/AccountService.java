package ru.runnerlite.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.SecUser;
import ru.runnerlite.entities.SecUserSimple;
import ru.runnerlite.entities.dto.Account;
import ru.runnerlite.entities.dto.SecUserDto;
import ru.runnerlite.repositories.SecUserRepository;
import ru.runnerlite.repositories.TeamRepository;

@Service
public class AccountService implements IAccountService {
	
	private final UserService userService;
	private final SecUserRepository userRepository;
	private final TeamRepository teamRepository;
	
	public AccountService(UserService userService, SecUserRepository userRepository, TeamRepository teamRepository) {
		this.userService = userService;
		this.userRepository = userRepository;
		this.teamRepository = teamRepository;
	}
	
	@Override
	public ResponseEntity<Account> register(SecUserDto user) {
		return null;
	}
	
	@Override
	public ResponseEntity<Account> get(Integer id) {
		SecUser secUser = userRepository.findById(id);
		SecUserSimple user = new SecUserSimple(
			secUser.getId(),
			secUser.getEmail(),
			secUser.getFullName(),
			secUser.getPassword(),
			secUser.getNickName(),
			secUser.getIsActive(),
			secUser.getUseNick(),
			secUser.getTeam().getId(),
			secUser.getBirthday().toString(),
			secUser.getSex()
		);
		Account account = new Account(user);
		return ResponseEntity.ok(account);
	}
}