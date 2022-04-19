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
@Table(name = "mailing_list")
public class MailingList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SEC_USERS_ID", nullable = false)
	@ToString.Exclude
	private SecUser secUsers;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "TEAMS_ID", nullable = false)
	@ToString.Exclude
	private Team teams;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "REF_SUBSCRIBE_TYPE_ID", nullable = false)
	@ToString.Exclude
	private RefSubscribeType refSubscribeType;
	
	public Integer getId() {
		return id;
	}
	
	public SecUser getSecUsers() {
		return secUsers;
	}
	
	public Team getTeams() {
		return teams;
	}
	
	public RefSubscribeType getRefSubscribeType() {
		return refSubscribeType;
	}
	
}