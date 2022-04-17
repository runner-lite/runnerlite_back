package ru.runnerlite.entities.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class RunningResultDto implements Serializable {
	private final Integer id;
	private final SecUserDto secUsers;
	private final Instant result;
	private final TeamsRunningCountDto teamsRunningCount;
	private final Integer finishPlace;
}
