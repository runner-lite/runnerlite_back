package ru.runnerlite.entities.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RefVolunteersPositionDto implements Serializable {
    private final Integer id;
    private final String name;
    private final String description;
    private final Boolean active;
}
