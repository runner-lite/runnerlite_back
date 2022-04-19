package ru.runnerlite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.SecUser;
import ru.runnerlite.entities.dto.SecUserDto;
import ru.runnerlite.repositories.SecUserRepository;

@Service
public class UserService implements IUserService {
	
	@Autowired
	SecUserRepository usersRepository;
	
	@Override
	public ResponseEntity<SecUserDto> register(SecUserDto userDto) {
		//TODO проверка на уникальность полей
		SecUser user = usersRepository.save(new SecUser(userDto));
		SecUserDto savedUser = new SecUserDto(user);
		return ResponseEntity.ok(savedUser);
	}
	
	@Override
	public ResponseEntity<SecUserDto> getById(Long id) {
		SecUserDto user = new SecUserDto(usersRepository.getById(id));
		return ResponseEntity.ok(user);
	}
}
