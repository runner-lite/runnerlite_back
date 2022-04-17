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
@Table(name = "teams_locations")
public class TeamsLocation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "TEAMS_ID", nullable = false)
	@ToString.Exclude
	private Team teams;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REF_CITIES")
	@ToString.Exclude
	private RefCity refCities;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "ALTERNATE_LOCATIONS")
	private Integer alternateLocations;
	
	@Column(name = "USE_ALTLOCATIONS", nullable = false)
	private Boolean useAltlocations = false;
	
	public Integer getId() {
		return id;
	}
	
	public Team getTeams() {
		return teams;
	}
	
	public RefCity getRefCities() {
		return refCities;
	}
	
	public String getAddress() {
		return address;
	}
	
	public Integer getAlternateLocations() {
		return alternateLocations;
	}
	
	public Boolean getUseAltlocations() {
		return useAltlocations;
	}
	
}