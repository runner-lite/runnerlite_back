package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.runnerlite.entities.SecUser;
import ru.runnerlite.entities.TeamsRunningCount;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamsRunningCountDto implements Serializable {
	private Integer id;
	private Instant runningDate;
	private Integer number;
	private String status;

	public TeamsRunningCountDto(TeamsRunningCount teamsRunningCount) {
		id = teamsRunningCount.getId();
		runningDate = teamsRunningCount.getRunningDate();
		number = teamsRunningCount.getNumber();
		status = teamsRunningCount.getStatus();
	}
}
