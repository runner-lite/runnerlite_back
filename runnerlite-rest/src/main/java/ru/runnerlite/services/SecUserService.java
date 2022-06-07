package ru.runnerlite.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.*;
import ru.runnerlite.entities.dto.SecUserDto;
import ru.runnerlite.entities.dto.UserNameDto;
import ru.runnerlite.repositories.*;
import ru.runnerlite.services.interfaces.ISecUserService;

import javax.transaction.Transactional;

@Service
public class SecUserService implements ISecUserService {
	
	private final SecUserRepository usersRepository;

	private final PasswordEncoder passwordEncoder;

	private final TeamRepository teamRepository;

	private final SecUsergroupsMemberRepository secUsergroupsMemberRepository;

	private final SecGroupRepository secGroupRepository;

	private final PhoneNumberRepository phoneNumberRepository;

	@Autowired
	public SecUserService(SecUserRepository usersRepository, PasswordEncoder passwordEncoder, TeamRepository teamRepository, SecUsergroupsMemberRepository secUsergroupsMemberRepository, SecGroupRepository secGroupRepository, PhoneNumberRepository phoneNumberRepository) {
		this.usersRepository = usersRepository;
		this.passwordEncoder = passwordEncoder;
		this.teamRepository = teamRepository;
		this.secUsergroupsMemberRepository = secUsergroupsMemberRepository;
		this.secGroupRepository = secGroupRepository;
		this.phoneNumberRepository = phoneNumberRepository;
	}
	
	@Override
	@Transactional
	public ResponseEntity<Integer> register(SecUserDto userDto) {
		Team homeTeam = teamRepository.getById(userDto.getTeamId());
		SecGroup defaultGroup = secGroupRepository.getById(3);
		SecUser user = usersRepository.save(new SecUser(
				null,
				userDto.getEmail(),
				userDto.getFullName(),
				passwordEncoder.encode(userDto.getPassword()),
				userDto.getNickName(),
				true,
				false,
				homeTeam,
				null,
				userDto.getBirthday(),
				userDto.getSex()
		));
		secUsergroupsMemberRepository.save(new SecUsergroupsMember(
				null,
				user,
				defaultGroup,
				homeTeam
		));
		phoneNumberRepository.save(new PhoneNumber(
			null,
			user,
			userDto.getPhone(),
			PhoneTypes.MOBILE.getTitle()
		));
		return ResponseEntity.ok(user.getId());
	}
	
	@Override
	public ResponseEntity<SecUserDto> getById(Integer id) {
		SecUserDto user = convert(usersRepository.getById(id.longValue()));
		return ResponseEntity.ok(user);
	}

	@Override
	public UserNameDto findByEmail(String email){
		SecUserDto secUserDto = usersRepository.findByEmail(email);
		UserNameDto user = new UserNameDto();
		user.setUserId(secUserDto.getId());
		user.setName(secUserDto.getUseNick() ? secUserDto.getNickName() : secUserDto.getFullName());
		return user;
	}

	SecUserDto convert(SecUser user) {
		SecUserDto userDto = new SecUserDto();
		userDto.setId(user.getId());
		userDto.setEmail(user.getEmail());
		userDto.setFullName(user.getFullName());
		userDto.setPassword(null);
		userDto.setNickName(user.getNickName());
		userDto.setIsActive(user.getIsActive());
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
		user.setPassword(userDto.getPassword() == null ? null : userDto.getPassword());
		user.setNickName(userDto.getNickName());
		user.setIsActive(userDto.getIsActive());
		user.setUseNick(userDto.getUseNick());
		user.setTeam(new Team(userDto.getTeamId()));
		user.setBirthday(userDto.getBirthday());
		user.setSex(userDto.getSex());
		return user;
	}
}
