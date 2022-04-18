package ru.runnerlite.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ref_cities")
public class RefCities {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "REF_REGIONS_ID")
	private Integer region;
	
	public RefCities(Integer id, String name, Integer region) {
		this.id = id;
		this.name = name;
		this.region = region;
	}
	
	public RefCities() {
	}
	
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
	
	public Integer getRegion() {
		return region;
	}
	
	public void setRegion(Integer region) {
		this.region = region;
	}
}
