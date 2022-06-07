package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefAgesCategoryDto implements Serializable {
	private Integer id;
	private String code;
	private String description;
	private Integer ageFrom;
	private Integer ageTo;
	private String sex;
}
