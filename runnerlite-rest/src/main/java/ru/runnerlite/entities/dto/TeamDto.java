package ru.runnerlite.entities.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TeamDto implements Serializable {
	private final Integer id;
	private final String name;
	private final String description;
	private final Double geoLat;
	private final Double geoLng;
	private final String geoDescription;
	private final Boolean active;
}
