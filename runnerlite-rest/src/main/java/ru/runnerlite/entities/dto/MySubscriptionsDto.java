package ru.runnerlite.entities.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MySubscriptionsDto implements Serializable {

    private final String teamsName; //имя команды
    private final Integer teamsId; //для логотипа id команды
    private final String nameSubscription; //наименование подписки
    private final String descriptionSubscription; //описание подписки
}
