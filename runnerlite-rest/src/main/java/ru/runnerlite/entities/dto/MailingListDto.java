package ru.runnerlite.entities.dto;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailingListDto implements Serializable {
	private Integer id;
	private SecUserDto secUsers;
	private TeamDto teams;
	private RefSubscribeTypeDto refSubscribeType;
}
