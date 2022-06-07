package ru.runnerlite.entities.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RunningPlaningDto {

    private TeamsRunningCountDto running;
    private Integer runnersCount;
    private Integer volunteersCount;

    public RunningPlaningDto(TeamsRunningCountDto running, Integer runnersCount, Integer volunteersCount) {
        this.running = running;
        this.runnersCount = runnersCount;
        this.volunteersCount = volunteersCount;
    }
}
