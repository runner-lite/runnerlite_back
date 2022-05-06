package ru.runnerlite.entities.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class RunningResultDto implements Serializable {
	private final Integer id;
	private final Integer runningId;
	private final Integer userId;
	private final Integer finishPlace;
	private final Instant result;
}
