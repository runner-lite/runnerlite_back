package ru.runnerlite.entities.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RefSubscribeTypeDto implements Serializable {
	private final Integer id;
	private final String code;
	private final String description;
	private final Boolean active;
	private final SecGroupDto secGroups;
	private final TeamDto teams;
}
