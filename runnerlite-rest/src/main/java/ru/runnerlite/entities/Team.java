package ru.runnerlite.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teams")
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "DESCRIPTION", nullable = false, length = 512)
	private String description;
	
	@Column(name = "GEO_LAT")
	private Double geoLat;
	
	@Column(name = "GEO_LNG")
	private Double geoLng;
	
	@Column(name = "GEO_DESCRIPTION")
	private String geoDescription;
	
	@Column(name = "ACTIVE", nullable = false)
	private Boolean active = false;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Double getGeoLat() {
		return geoLat;
	}
	
	public void setGeoLat(Double geoLat) {
		this.geoLat = geoLat;
	}
	
	public Double getGeoLng() {
		return geoLng;
	}
	
	public void setGeoLng(Double geoLng) {
		this.geoLng = geoLng;
	}
	
	public String getGeoDescription() {
		return geoDescription;
	}
	
	public void setGeoDescription(String geoDescription) {
		this.geoDescription = geoDescription;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}