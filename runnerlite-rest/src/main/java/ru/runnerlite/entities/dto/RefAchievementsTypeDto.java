package ru.runnerlite.entities.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RefAchievementsTypeDto implements Serializable {
	private final Integer id;
	private final String code;
	private final String description;
	private final String picturePath;
}
