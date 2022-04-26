package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecUserDto implements Serializable {
	private Integer id;
	private String email;
	private String fullName;
	private String password;
	private String nickName;
	private Boolean isActive;
	private Boolean useNick;
	private Integer teamId;
	private Instant birthday;
	private String sex;
}
