package ru.runnerlite.entities.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RunningResultDto implements Serializable {
	private final Integer id;
	private final Integer runningId;
	private final Integer userId;
	private final Integer finishPlace;
	private final Integer result;
}
