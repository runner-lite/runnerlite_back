package ru.runnerlite.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ref_districts")

public class RefDistrict {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name = "NAME", nullable = false)
	private String name;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "REF_CITIES_ID", nullable = false)
	@ToString.Exclude
	private RefCity refCity;

	@OneToMany(mappedBy = "refDistricts", fetch = FetchType.LAZY)
	private List<Location> locations;
}
