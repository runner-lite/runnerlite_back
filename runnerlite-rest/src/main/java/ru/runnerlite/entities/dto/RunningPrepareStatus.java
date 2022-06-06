package ru.runnerlite.entities.dto;

import java.util.List;

/**
 * DTO для раздела "Статус набора команды волонтёров" в кабинете руководителя забегов
 */
public class RunningPrepareStatus {
	
	private TeamsRunningCountDto running;
	private List<TeamsVolunteerDto> needVolunteers;
	private List<VolunteerSimpleDto> volunteers;
	
	private Integer recruitmentPercentage;
	
	public RunningPrepareStatus() {
	}
	
	public RunningPrepareStatus(TeamsRunningCountDto running, List<TeamsVolunteerDto> needVolunteers, List<VolunteerSimpleDto> volunteers) {
		this.running = running;
		this.needVolunteers = needVolunteers;
		this.volunteers = volunteers;
	}
	
	public TeamsRunningCountDto getRunning() {
		return running;
	}
	
	public RunningPrepareStatus(TeamsRunningCountDto running, Integer recruitmentPercentage) {
		this.running = running;
		this.recruitmentPercentage = recruitmentPercentage;
	}
	
		public List<TeamsVolunteerDto> getNeedVolunteers() {
		return needVolunteers;
	}

	public List<VolunteerSimpleDto> getVolunteers() {
		return volunteers;
	}
	
	public Integer getRecruitmentPercentage() {
		return recruitmentPercentage;
	}
	
	public void setRunning(TeamsRunningCountDto running) {
		this.running = running;
	}
	
	public void setNeedVolunteers(List<TeamsVolunteerDto> needVolunteers) {
		this.needVolunteers = needVolunteers;
	}

	public void setVolunteers(List<VolunteerSimpleDto> volunteers) {
		this.volunteers = volunteers;
	}
	
	public void setRecruitmentPercentage(Integer recruitmentPercentage) {
		this.recruitmentPercentage = recruitmentPercentage;
	}
}
