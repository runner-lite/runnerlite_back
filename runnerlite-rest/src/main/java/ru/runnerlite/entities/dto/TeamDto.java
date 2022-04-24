package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto implements Serializable {
	private Integer id;
	private String name;
	private String description;
	private Double geoLat;
	private Double geoLng;
	private String geoDescription;
	private Boolean active;
}
