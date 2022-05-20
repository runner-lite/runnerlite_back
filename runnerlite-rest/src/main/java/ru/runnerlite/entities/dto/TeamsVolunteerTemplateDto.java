package ru.runnerlite.entities.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TeamsVolunteerTemplateDto implements Serializable {
    private final Integer id;
    private final TeamDto team;
    private final RefVolunteersPositionDto refVolunteersPosition;
    private final Integer qty;
}
