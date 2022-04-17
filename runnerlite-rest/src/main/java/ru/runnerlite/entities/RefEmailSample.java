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
@Table(name = "ref_email_samples")
public class RefEmailSample {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name = "NAME", nullable = false, length = 45)
	private String name;
	
	@Column(name = "CONTENT", nullable = false, length = 45)
	private String content;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "REF_SUBSCRIBE_TYPE", nullable = false)
	@ToString.Exclude
	private RefSubscribeType refSubscribeType;
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getContent() {
		return content;
	}
	
	public RefSubscribeType getRefSubscribeType() {
		return refSubscribeType;
	}
	
}