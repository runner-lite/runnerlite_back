package ru.runnerlite.entities.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AchievementDto implements Serializable {
	private final Integer id;
	private final SecUserDto secUsers;
	private final RefAchievementsTypeDto refAchievementsType;
	private final RunningResultDto runningResults;
}
