package ru.runnerlite.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sec_users")
public class SecUserSimple {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
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
	
	@Column(name = "TEAMS_ID")
	private Integer teamId;
	
	@Column(name = "BIRTHDAY", nullable = false)
	private String birthday;
	
	@Column(name = "SEX", nullable = false)
	private String sex;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		SecUserSimple that = (SecUserSimple) o;
		return id != null && Objects.equals(id, that.id);
	}
	
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
