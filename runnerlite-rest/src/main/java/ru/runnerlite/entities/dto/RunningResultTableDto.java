package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RunningResultTableDto {
    private Instant runningDate; //дата забега
    private Integer runningNumber; //номер забега
    private Integer runnersCount; //количество бегунов участвующих в забеге
    private List<TourneyTableDto> tourneyTableDto; //турнирная таблица
}
