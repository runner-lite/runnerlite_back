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
@Table(name = "sec_identity")
public class SecIdentity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SEC_USERS_ID", nullable = false)
	@ToString.Exclude
	private SecUser secUsers;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SEC_IDENTITY_CODE_ID", nullable = false)
	@ToString.Exclude
	private SecIdentityCode secIdentityCode;
	
	public Integer getId() {
		return id;
	}
	
	public SecUser getSecUsers() {
		return secUsers;
	}
	
	public SecIdentityCode getSecIdentityCode() {
		return secIdentityCode;
	}
	
}