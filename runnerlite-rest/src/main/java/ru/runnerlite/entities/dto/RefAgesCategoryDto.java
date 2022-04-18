package ru.runnerlite.entities.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RefAgesCategoryDto implements Serializable {
	private final Integer id;
	private final String code;
	private final String description;
	private final Integer ageFrom;
	private final Integer ageTo;
	private final String sex;
}
