package ru.runnerlite.entities.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class TeamsRunningCountDto implements Serializable {
	private final Integer id;
	private final Instant runningDate;
	private final Integer number;
	private final String status;
}
