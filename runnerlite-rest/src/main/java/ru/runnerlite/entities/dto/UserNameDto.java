package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNameDto   implements Serializable {
    private Integer userId; //id user
    private String name; //полное имя или ник в зависимости от галочки
}
