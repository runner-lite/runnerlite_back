package ru.runnerlite.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "locations")
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;

//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "TEAMS_ID", nullable = false)
//	private Team teams;
	
	//	fetch = FetchType.LAZY,
	@ManyToOne(optional = false)
	@JoinColumn(name = "REF_DISTRICTS_ID", nullable = false)
	private RefDistrict refDistricts;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "ALTERNATE_LOCATIONS_ID")
	private Integer alternateLocationsId;
	
	@Column(name = "USE_ALTLOCATIONS", nullable = false)
	private Boolean useAltlocations = false;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

//	public Team getTeams() {
//		return teams;
//	}

//	public void setTeams(Team teams) {
//		this.teams = teams;
//	}
	
	public RefDistrict getRefDistricts() {
		return refDistricts;
	}
	
	public void setRefDistricts(RefDistrict refDistricts) {
		this.refDistricts = refDistricts;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Integer getAlternateLocationsId() {
		return alternateLocationsId;
	}
	
	public void setAlternateLocationsId(Integer alternateLocationsId) {
		this.alternateLocationsId = alternateLocationsId;
	}
	
	public Boolean getUseAltlocations() {
		return useAltlocations;
	}
	
	public void setUseAltlocations(Boolean useAltlocations) {
		this.useAltlocations = useAltlocations;
	}
	
}