package ru.runnerlite.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "teams_volunteers")
public class TeamsVolunteer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "TEAMS_RUNNING_COUNT_ID", nullable = false)
	@ToString.Exclude
	private TeamsRunningCount teamsRunningCount;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "REF_VOLUNTEERS_POSITION_ID", nullable = false)
	@ToString.Exclude
	private RefVolunteersPosition refVolunteersPosition;
	
	@Column(name = "NEED_VOLUNTEER_QTY", nullable = false)
	private Integer needVolunteerQty;
	
	@Column(name = "MIN_VOLUNTEER_QTY", nullable = false)
	private Integer minVolunteerQty;
	
	public Integer getId() {
		return id;
	}
	
	public TeamsRunningCount getTeamsRunningCount() {
		return teamsRunningCount;
	}
	
	public RefVolunteersPosition getRefVolunteersPosition() {
		return refVolunteersPosition;
	}
	
	public Integer getNeedVolunteerQty() {
		return needVolunteerQty;
	}
	
	public Integer getMinVolunteerQty() {
		return minVolunteerQty;
	}
	
}