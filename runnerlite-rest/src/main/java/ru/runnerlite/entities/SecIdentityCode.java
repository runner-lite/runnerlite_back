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
@Table(name = "sec_identity_code")
public class SecIdentityCode {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name = "CODE", nullable = false, length = 10)
	private String code;
	
	@Column(name = "ACTIVE", nullable = false, length = 45)
	private String active;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "REF_IDENTITY_RUNSYSTEMS_ID", nullable = false)
	@ToString.Exclude
	private RefIdentityRunsystem refIdentityRunsystems;
	
	public Integer getId() {
		return id;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getActive() {
		return active;
	}
	
	public RefIdentityRunsystem getRefIdentityRunsystems() {
		return refIdentityRunsystems;
	}
	
}