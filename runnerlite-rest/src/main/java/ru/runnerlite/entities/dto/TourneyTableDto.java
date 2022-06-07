package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourneyTableDto implements Serializable {
	private Integer finishPlace; // номер карточки места на финише
	private Integer userId; // id бегуна
	private String userFullName; // полное имя бегуна
	private String userNick; // ник бегуна
	private Integer result; // результат забега в секундах
	private String resultString; // результат забега в минутах(часах)

	public TourneyTableDto(Integer finishPlace, Integer userId, String userFullName, String userNick, Integer result) {
		this.finishPlace = finishPlace;
		this.userId = userId;
		this.userFullName = userFullName;
		this.userNick = userNick;
		this.result = result;
	}
}
