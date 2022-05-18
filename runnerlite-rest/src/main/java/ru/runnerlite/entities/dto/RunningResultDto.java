package ru.runnerlite.entities.dto;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RunningResultDto implements Serializable {
	private Integer id; // id строки в таблице RunningResult
	private Integer runningId; // id строки в таблице TeamsRunningCount
	private Integer userId; // id бегуна
	private Integer finishPlace; // номер карточки места на финише
	private Integer result; // результат забега в секундах
	private Instant runningDate; //дата забега
	private Integer runningNumber; //номер забега
	private String teamsName; //имя команды
	private String description; //описание команды
	private String geoDescription; //описание места забега
	private Integer teamsId; //для логотипа
}
