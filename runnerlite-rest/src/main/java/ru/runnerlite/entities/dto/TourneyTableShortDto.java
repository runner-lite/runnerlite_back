package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourneyTableShortDto {
    private Integer finishPlace; // номер карточки места на финише
    private Integer userId; // id бегуна
    private String resultString; // результат забега в минутах(часах)
    private String name; // результат забега в минутах(часах)
}
