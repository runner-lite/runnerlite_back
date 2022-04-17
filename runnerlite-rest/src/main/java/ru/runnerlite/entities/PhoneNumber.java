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
@Table(name = "phone_numbers")
public class PhoneNumber {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SEC_USERS_ID", nullable = false)
	@ToString.Exclude
	private SecUser secUsers;
	
	@Column(name = "PHONE_NUMBER", nullable = false, length = 20)
	private String phoneNumber;
	
	@Lob
	@Column(name = "TYPE", nullable = false)
	private String type;
	
	public Integer getId() {
		return id;
	}
	
	public SecUser getSecUsers() {
		return secUsers;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getType() {
		return type;
	}
	
}