package ru.runnerlite.entities.dto;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefSubscribeTypeDto implements Serializable {
	private Integer id;
	private String code;
	private String description;
	private Boolean active;
	private SecGroupDto secGroups;
	private TeamDto teams;
}
