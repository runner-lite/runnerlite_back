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
@Table(name = "ref_cities")
public class RefCity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "REF_DISTRICTS", nullable = false)
	@ToString.Exclude
	private RefDistrict refDistricts;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	public Integer getId() {
		return id;
	}
	
	public RefDistrict getRefDistricts() {
		return refDistricts;
	}
	
	public String getName() {
		return name;
	}
	
}