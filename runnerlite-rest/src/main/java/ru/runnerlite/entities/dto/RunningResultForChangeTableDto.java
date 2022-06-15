package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RunningResultForChangeTableDto {
    private Integer id; // id строки в таблице RunningResult
    private Integer secUserId; // id бегуна
    private String fullNameUser; // полное имя бегуна
    private String nick; // ник бегуна
    private Integer result; // результат забега в секундах
    private Integer teamsRunningCountId; // id забега teamsRunningCount
    private Integer finishPlace; // номер карточки места на финише
    private Boolean useNick; // использовать полное имя (0) или ник (1) в зависимости от галочки

    public RunningResultForChangeTableDto(Integer secUserId, String fullNameUser, String nick, Integer teamsRunningCountId, Boolean useNick) {
        this.secUserId = secUserId;
        this.fullNameUser = fullNameUser;
        this.nick = nick;
        this.teamsRunningCountId = teamsRunningCountId;
        this.useNick = useNick;
    }
}
