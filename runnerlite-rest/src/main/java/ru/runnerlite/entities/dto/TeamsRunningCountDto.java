package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.runnerlite.entities.TeamsRunningCount;

import java.io.Serializable;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
public class TeamsRunningCountDto implements Serializable {
	private Integer id;
	private Integer teamId;
	private Instant runningDate;
	private Integer number;
	private String status;

	public TeamsRunningCountDto(TeamsRunningCount teamsRunningCount) {
		id = teamsRunningCount.getId();
		teamId = teamsRunningCount.getTeams().getId();
		runningDate = teamsRunningCount.getRunningDate();
		number = teamsRunningCount.getNumber();
		status = teamsRunningCount.getStatus();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getTeamId() {
		return teamId;
	}
	
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	public Instant getRunningDate() {
		return runningDate;
	}
	
	public void setRunningDate(String runningDate) {
		this.runningDate = Instant.parse(runningDate);
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
