package ru.runnerlite.entities.dto;

import lombok.Getter;
import lombok.Setter;
import ru.runnerlite.entities.TeamsVolunteer;

@Getter
@Setter
public class TeamsVolunteerDto {
	
	private Integer id;
	private Integer runningId;
	private String volunteerPosition;
	private Integer needVolunteerQty;
	private Integer minVolunteerQty;
	
	public TeamsVolunteerDto() {
	}
	
	public TeamsVolunteerDto(Integer id, Integer runningId, String volunteerPosition, Integer needVolunteerQty, Integer minVolunteerQty) {
		this.id = id;
		this.runningId = runningId;
		this.volunteerPosition = volunteerPosition;
		this.needVolunteerQty = needVolunteerQty;
		this.minVolunteerQty = minVolunteerQty;
	}
	
	public TeamsVolunteerDto(TeamsVolunteer tv) {
		this.id = tv.getId();
		this.runningId = tv.getTeamsRunningCount().getId();
		this.volunteerPosition = tv.getRefVolunteersPosition().getName();
		this.needVolunteerQty = tv.getNeedVolunteerQty();
		this.minVolunteerQty = tv.getMinVolunteerQty();
	}
}