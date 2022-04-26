package ru.runnerlite.entities.dto;

import lombok.Data;
import ru.runnerlite.entities.SecUser;
import ru.runnerlite.entities.TeamsRunningCount;

import java.io.Serializable;
import java.time.Instant;

@Data
public class TeamsRunningCountDto implements Serializable {
	private final Integer id;
	private final Instant runningDate;
	private final Integer number;
	private final String status;

	public TeamsRunningCountDto(TeamsRunningCount teamsRunningCount) {
		id = teamsRunningCount.getId();
		runningDate = teamsRunningCount.getRunningDate();
		number = teamsRunningCount.getNumber();
		status = teamsRunningCount.getStatus();
	}
}
