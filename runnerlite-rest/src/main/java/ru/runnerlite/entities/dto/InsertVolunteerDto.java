package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertVolunteerDto {

    @NotNull
    private Integer teamsRunningCountId;
    @NotNull
    private Integer volunteersPosition;
}
