package ru.runnerlite.services;

import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.SecUser;
import ru.runnerlite.entities.Team;
import ru.runnerlite.entities.dto.SecUserDto;
import ru.runnerlite.repositories.SecUserRepository;
import ru.runnerlite.services.interfaces.ISecUserService;

@Service
@Getter
public class SecSecUserService implements ISecUserService {
	
	
	private final SecUserRepository usersRepository;
	
	public SecSecUserService(SecUserRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	@Override
	public ResponseEntity<SecUserDto> register(SecUserDto userDto) {
		//TODO проверка на уникальность полей
		SecUser user = usersRepository.save(convert(userDto));
		SecUserDto savedUser = convert(user);
		return ResponseEntity.ok(savedUser);
	}
	
	@Override
	public ResponseEntity<SecUserDto> getById(Integer id) {
		SecUserDto user = convert(usersRepository.findById(id));
		return ResponseEntity.ok(user);
	}
	
	SecUserDto convert(SecUser user) {
		SecUserDto userDto = new SecUserDto();
		userDto.setId(user.getId());
		userDto.setEmail(user.getEmail());
		userDto.setFullName(user.getFullName());
//		userDto.setPassword(null);
		userDto.setNickName(user.getNickName());
		userDto.setUseNick(user.getUseNick());
		userDto.setTeamId(user.getTeam().getId());
		userDto.setBirthday(user.getBirthday());
		userDto.setSex(user.getSex());
		return userDto;
	}
	
	private SecUser convert(SecUserDto userDto) {
		SecUser user = new SecUser();
		user.setId(userDto.getId());
		user.setEmail(userDto.getEmail());
		user.setFullName(userDto.getFullName());
//		user.setPassword(null);
		user.setNickName(userDto.getNickName());
		user.setUseNick(userDto.getUseNick());
		user.setTeam(new Team(userDto.getTeamId()));
		user.setBirthday(userDto.getBirthday());
		user.setSex(userDto.getSex());
		return user;
	}
}
