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
import javax.persistence.OneToOne;
import javax.persistence.Table;

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