package ru.runnerlite.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "teams")
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	//fetch = FetchType.LAZY,
	@MapsId
	@OneToOne(optional = false)
	@JoinColumn(name = "ID", nullable = false)
	private Location location;
	
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
	
	public Team(Integer id) {
		this.id = id;
	}
}