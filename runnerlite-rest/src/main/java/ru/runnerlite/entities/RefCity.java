package ru.runnerlite.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ref_cities")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RefCity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "REF_REGIONS_ID")
	private Integer region;

	@OneToMany(mappedBy = "refCity", fetch = FetchType.LAZY)
	private List<RefDistrict> districts;

}
