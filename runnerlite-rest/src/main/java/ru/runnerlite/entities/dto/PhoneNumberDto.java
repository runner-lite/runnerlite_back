package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumberDto implements Serializable {
	private Integer id;
	private SecUserDto secUsers;
	private String phoneNumber;
	private String type;
}
