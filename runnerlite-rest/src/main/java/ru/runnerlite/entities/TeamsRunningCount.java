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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "teams_running_count")
public class TeamsRunningCount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "TEAMS_ID", nullable = false)
	@ToString.Exclude
	private Team teams;
	
	@Column(name = "RUNNING_DATE", nullable = false)
	private Instant runningDate;
	
	@Column(name = "NUMBER", nullable = false)
	private Integer number;
	
	@Lob
	@Column(name = "STATUS", nullable = false)
	private String status;
	
	public Integer getId() {
		return id;
	}
	
	public Team getTeams() {
		return teams;
	}
	
	public Instant getRunningDate() {
		return runningDate;
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public String getStatus() {
		return status;
	}
	
}