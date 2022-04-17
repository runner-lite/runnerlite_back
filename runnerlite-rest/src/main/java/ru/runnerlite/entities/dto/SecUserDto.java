package ru.runnerlite.entities.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.runnerlite.entities.SecUser;
import ru.runnerlite.entities.Team;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
public class SecUserDto implements Serializable {
	private Long id;
	private String email;
	private String fullName;
	private String password;
	private String nickName;
	private Boolean active;
	private Boolean useNick;
	private Team team;
	private Instant birthday;
	private String sex;
	
	public SecUserDto(SecUser secUser) {
		id = secUser.getId();
		email = secUser.getEmail();
		fullName = secUser.getFullName();
		password = secUser.getPassword();
		nickName = secUser.getNickName();
		active = secUser.getIsActive();
		useNick = secUser.getUseNick();
		team = secUser.getTeam();
		birthday = secUser.getBirthday();
		sex = secUser.getSex();
	}
}
