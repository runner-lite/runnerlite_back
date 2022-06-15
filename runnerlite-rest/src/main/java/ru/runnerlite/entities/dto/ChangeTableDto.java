package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeTableDto implements Serializable {
	private Integer finishPlace; // номер карточки места на финише
	private Integer userId; // id бегуна
	private String userName; // используемое имя бегуна
	private String resultString; // результат забега в минутах(часах)

	public ChangeTableDto(Integer userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}
}
