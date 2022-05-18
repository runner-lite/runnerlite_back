package ru.runnerlite.entities.dto;

import lombok.*;
import ru.runnerlite.entities.SecUserSimple;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {
	
	private SecUserSimple user;
}
