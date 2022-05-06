package ru.runnerlite.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "LOCATION", nullable = false)
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