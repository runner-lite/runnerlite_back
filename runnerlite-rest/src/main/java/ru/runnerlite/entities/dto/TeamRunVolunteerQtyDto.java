package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
public class TeamRunVolunteerQtyDto implements Serializable {
    private Integer teamId;
    private Integer positionId;
    private String position; // название позиции волонтера
    private Integer qty; // нужное количество
}
