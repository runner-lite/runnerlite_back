package ru.runnerlite.entities.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MailingListDto implements Serializable {
	private final Integer id;
	private final SecUserDto secUsers;
	private final TeamDto teams;
	private final RefSubscribeTypeDto refSubscribeType;
}
