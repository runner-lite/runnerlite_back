package ru.runnerlite.entities.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmergencyContactDto implements Serializable {
	private final Integer id;
	private final String name;
	private final String phoneNumber;
	private final String relativeType;
	private final SecUserDto secUsers;
	private final Boolean mainContact;
}
