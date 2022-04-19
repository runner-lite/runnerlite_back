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
@Table(name = "emergency_contact")
public class EmergencyContact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "PHONE_NUMBER", nullable = false, length = 20)
	private String phoneNumber;
	
	@Lob
	@Column(name = "RELATIVE_TYPE", nullable = false)
	private String relativeType;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SEC_USERS_ID", nullable = false)
	@ToString.Exclude
	private SecUser secUsers;
	
	@Column(name = "MAIN_CONTACT", nullable = false)
	private Boolean mainContact = false;
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getRelativeType() {
		return relativeType;
	}
	
	public SecUser getSecUsers() {
		return secUsers;
	}
	
	public Boolean getMainContact() {
		return mainContact;
	}
	
}