package ru.runnerlite.entities.dto;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecGroupDto implements Serializable {
	private Integer id;
	private String name;
	private Boolean active;
	private String description;
}
