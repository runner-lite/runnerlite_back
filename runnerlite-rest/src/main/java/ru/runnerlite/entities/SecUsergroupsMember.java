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
@Table(name = "sec_usergroups_member")
public class SecUsergroupsMember {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SEC_USERS_ID", nullable = false)
	@ToString.Exclude
	private SecUser secUsers;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SEC_GROUPS_ID", nullable = false)
	@ToString.Exclude
	private SecGroup secGroups;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SEC_TEAMS_ID", nullable = false)
	@ToString.Exclude
	private Team secTeams;
	
	public Integer getId() {
		return id;
	}
	
	public SecUser getSecUsers() {
		return secUsers;
	}
	
	public SecGroup getSecGroups() {
		return secGroups;
	}
	
	public Team getSecTeams() {
		return secTeams;
	}
	
}