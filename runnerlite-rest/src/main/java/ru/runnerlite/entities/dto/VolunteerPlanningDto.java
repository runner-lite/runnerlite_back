package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerPlanningDto implements Serializable {
    private Integer id; //id волонтера
    private Integer userId; //id user
    private String fullName; //полное имя волонтера
    private Integer status; //статус запроса
}
