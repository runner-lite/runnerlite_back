package ru.runnerlite.entities.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SecGroupDto implements Serializable {
	private final Integer id;
	private final String name;
	private final Boolean active;
	private final String description;
}
