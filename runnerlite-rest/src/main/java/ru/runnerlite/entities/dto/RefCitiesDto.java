package ru.runnerlite.entities.dto;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefCitiesDto implements Serializable {
	private Integer id;
	private String name;
}
