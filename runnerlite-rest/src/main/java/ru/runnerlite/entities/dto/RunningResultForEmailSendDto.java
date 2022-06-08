package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RunningResultForEmailSendDto {
    private String email;
    private String runnerName;
    private Integer runningNumber;
    private Instant runningDate;
    private String teamName;
    private Integer position;
    private Integer resultTime;
}
