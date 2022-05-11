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
	private final Instant runningDate; //дата забега
	private final Integer runningNumber; //номер забега
	private final String teamsName; //имя команды
	private final String description; //описание команды
	private final String geoDescription; //описание места забега
	private final Integer teamsId; //для логотипа
}
