package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecUserDto implements Serializable {
	private Integer id;

	@Email
	private String email;

	@NotBlank
	private String fullName;

	@NotBlank
	private String password;

	private String nickName;

	@NotBlank
	private String phone;

	private Boolean isActive;

	private Boolean useNick;

	@NotNull
	private Integer teamId;

	@NotNull
	private Instant birthday;

	@NotBlank
	private String sex;

	public SecUserDto(Integer id, String email, String fullName, String nickName, Boolean useNick) {
		this.id = id;
		this.email = email;
		this.fullName = fullName;
		this.nickName = nickName;
		this.useNick = useNick;
	}
}
