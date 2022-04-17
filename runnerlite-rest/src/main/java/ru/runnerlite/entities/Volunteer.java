package ru.runnerlite.entities;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "volunteers")
public class Volunteer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SEC_USERS_ID", nullable = false)
	@ToString.Exclude
	private SecUser secUsers;
	
	@Column(name = "STATUS", nullable = false)
	private Boolean status = false;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "REF_VOLUNTEERS_POSITION_ID", nullable = false)
	@ToString.Exclude
	private RefVolunteersPosition refVolunteersPosition;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "TEAMS_RUNNING_COUNT_ID", nullable = false)
	@ToString.Exclude
	private TeamsRunningCount teamsRunningCount;
	
	public Integer getId() {
		return id;
	}
	
	public SecUser getSecUsers() {
		return secUsers;
	}
	
	public Boolean getStatus() {
		return status;
	}
	
	public RefVolunteersPosition getRefVolunteersPosition() {
		return refVolunteersPosition;
	}
	
	public TeamsRunningCount getTeamsRunningCount() {
		return teamsRunningCount;
	}
	
}