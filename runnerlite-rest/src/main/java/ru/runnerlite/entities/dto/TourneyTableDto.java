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
	private String fullName; // полное имя бегуна
	private String nick; // ник бегуна
	private Integer result; // результат забега в секундах
	private Boolean useNick; // галочка использования ника
}
