package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.runnerlite.entities.RefDistrict;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class RefCitiesDto implements Serializable {
	private final Integer id;
	private final String name;
}
