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
@Table(name = "runner_count")
public class RunnerCount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SEC_USERS_ID", nullable = false)
	@ToString.Exclude
	private SecUser secUser;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "TEAMS_RUNNING_COUNT_ID", nullable = false)
	@ToString.Exclude
	private TeamsRunningCount teamsRunningCount;

	@Column(name = "STATUS", nullable = false)
	private Integer status;
	
	public Integer getId() {
		return id;
	}
	
	public SecUser getSecUser() {
		return secUser;
	}
	
	public TeamsRunningCount getTeamsRunningCount() {
		return teamsRunningCount;
	}

	public Integer getStatus() {
		return status;
	}
}