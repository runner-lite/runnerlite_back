package ru.runnerlite.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "ref_identity_runsystems")
public class RefIdentityRunsystem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name = "NAME", nullable = false, length = 45)
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "PREFIX", nullable = false, length = 45)
	private String prefix;
	
	@Column(name = "ACTIVE", nullable = false)
	private Boolean active = false;
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public Boolean getActive() {
		return active;
	}
	
}