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