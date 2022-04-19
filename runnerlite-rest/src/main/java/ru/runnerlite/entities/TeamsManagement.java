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
@Table(name = "teams_management")
public class TeamsManagement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "REF_MANAGER_TYPE_ID", nullable = false)
	@ToString.Exclude
	private RefManagerType refManagerType;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SEC_USERS_ID", nullable = false)
	@ToString.Exclude
	private SecUser secUsers;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "TEAMS_ID", nullable = false)
	@ToString.Exclude
	private Team teams;
	
	public Integer getId() {
		return id;
	}
	
	public RefManagerType getRefManagerType() {
		return refManagerType;
	}
	
	public SecUser getSecUsers() {
		return secUsers;
	}
	
	public Team getTeams() {
		return teams;
	}
	
}