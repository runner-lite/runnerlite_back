package ru.runnerlite.entities;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "running_results")
public class RunningResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SEC_USERS_ID", nullable = false)
	@ToString.Exclude
	private SecUser secUsers;
	
	@Column(name = "RESULT", nullable = false)
	private Instant result;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "TEAMS_RUNNING_COUNT_ID", nullable = false)
	@ToString.Exclude
	private TeamsRunningCount teamsRunningCount;
	
	@Column(name = "FINISH_PLACE", nullable = false)
	private Integer finishPlace;
	
	public Integer getId() {
		return id;
	}
	
	public SecUser getSecUsers() {
		return secUsers;
	}
	
	public Instant getResult() {
		return result;
	}
	
	public TeamsRunningCount getTeamsRunningCount() {
		return teamsRunningCount;
	}
	
	public Integer getFinishPlace() {
		return finishPlace;
	}
	
}