package ru.runnerlite.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "locations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;

//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "TEAMS_ID", nullable = false)
//	private Team teams;

	@OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
	private List<Team> teams;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "REF_DISTRICTS_ID", nullable = false)
	private RefDistrict refDistricts;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "ALTERNATE_LOCATIONS_ID")
	private Integer alternateLocationsId;
	
	@Column(name = "USE_ALTLOCATIONS", nullable = false)
	private Boolean useAltlocations;
	
}