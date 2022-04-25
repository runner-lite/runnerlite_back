package ru.runnerlite.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.runnerlite.entities.dto.SecUserDto;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "sec_users")
public class SecUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "LOGIN_EMAIL", nullable = false)
	private String email;
	
	@Column(name = "FULLNAME", nullable = false)
	private String fullName;
	
	@Column(name = "PASSWORD", nullable = false, length = 100)
	private String password;
	
	@Column(name = "NICK_NAME", length = 100)
	private String nickName;
	
	@Column(name = "ACTIVE", nullable = false)
	private Boolean isActive = false;
	
	@Column(name = "USE_NICK", nullable = false)
	private Boolean useNick = false;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "TEAMS_ID", nullable = false)
	@ToString.Exclude
	private Team team;

	@ManyToMany
	@JoinTable(
			name = "sec_usergroups_member",
			joinColumns = @JoinColumn(
					name = "SEC_USERS_ID",
					referencedColumnName = "ID"
			),
			inverseJoinColumns = @JoinColumn(
					name = "SEC_GROUPS_ID",
					referencedColumnName = "ID"
			)
	)
	private Set<SecGroup> secGroup;

	
	@Column(name = "BIRTHDAY", nullable = false)
	private Instant birthday;
	
	@Column(name = "SEX", nullable = false)
	private String sex;


	
	public SecUser (SecUserDto secUserDto) {
		id = secUserDto.getId();
		email = secUserDto.getEmail();
		fullName = secUserDto.getFullName();
		password = secUserDto.getPassword();
		nickName = secUserDto.getNickName();
		isActive = secUserDto.getActive();
		useNick = secUserDto.getUseNick();
		team = secUserDto.getTeam();
		birthday = secUserDto.getBirthday();
		sex = secUserDto.getSex();
	}
	
}