package ru.runnerlite.entities.dto;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AchievementDto implements Serializable {
	private Integer id;
	private SecUserDto secUsers;
	private RefAchievementsTypeDto refAchievementsType;
	private RunningResultDto runningResults;
}
