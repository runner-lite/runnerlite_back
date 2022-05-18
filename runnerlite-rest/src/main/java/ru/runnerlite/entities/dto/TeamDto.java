package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto implements Serializable {
	private Integer id; // id поля команды (для логотипа)
	private String name; // наименование беговой команды
	private String description; // описание беговой команды
	private Double geoLat; // координата «широта» геолокации места сбора
	private Double geoLng; // координата «долгота» геолокации места сбора
	private String geoDescription; // описание геолокации места сбора
	private Boolean active; // флаг доступности команды
}
