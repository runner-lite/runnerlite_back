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
@Table(name = "ref_subscribe_type")
public class RefSubscribeType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name = "CODE", nullable = false, length = 45)
	private String code;
	
	@Column(name = "DESCRIPTION", nullable = false, length = 512)
	private String description;
	
	@Column(name = "ACTIVE", nullable = false)
	private Boolean active = false;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SEC_GROUPS_ID", nullable = false)
	@ToString.Exclude
	private SecGroup secGroups;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "TEAMS_ID", nullable = false)
	@ToString.Exclude
	private Team teams;
	
	public Integer getId() {
		return id;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public SecGroup getSecGroups() {
		return secGroups;
	}
	
	public Team getTeams() {
		return teams;
	}
	
}