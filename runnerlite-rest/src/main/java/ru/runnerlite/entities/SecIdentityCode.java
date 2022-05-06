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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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