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
@Table(name = "ref_districts")
public class RefDistrict {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "REF_REGIONS", nullable = false)
	@ToString.Exclude
	private RefRegion refRegions;
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public RefRegion getRefRegions() {
		return refRegions;
	}
	
}