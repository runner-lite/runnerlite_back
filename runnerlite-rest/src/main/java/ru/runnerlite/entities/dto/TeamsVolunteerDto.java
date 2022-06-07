package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamsVolunteerDto implements Serializable {
	private Integer idTeamsVolunteer; //id таблицы TeamsVolunteer
	private Integer refVolunteersPositionId; //id строки из таблицы RefVolunteersPosition (нужна для участия в забеге волонтером)
	private String positionName; //позиция волонтера
	private String positionDescription; //описание позиции волонтера
	private Integer qty; // необходимое кол-во волонтеров
	private List<VolunteerPlanningDto> volunteerDtoList; //список волонтеров набранных в команду на данную позицию
	
	public TeamsVolunteerDto(Integer idTeamsVolunteer, Integer refVolunteersPositionId, String positionName, String positionDescription, Integer qty) {
		this.idTeamsVolunteer = idTeamsVolunteer;
		this.refVolunteersPositionId = refVolunteersPositionId;
		this.positionName = positionName;
		this.positionDescription = positionDescription;
		this.qty = qty;
	}
}