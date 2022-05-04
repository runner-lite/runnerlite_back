package ru.runnerlite.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PhoneTypes {

    MOBILE("Сотовый"),
    HOME("Домашний"),
    WORK("Рабочий");

    private String title;
}
