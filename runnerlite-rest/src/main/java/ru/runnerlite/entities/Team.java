package ru.runnerlite.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "teams")
@JsonIgnoreProperties("hibernateLazyInitializer")
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
	
}
