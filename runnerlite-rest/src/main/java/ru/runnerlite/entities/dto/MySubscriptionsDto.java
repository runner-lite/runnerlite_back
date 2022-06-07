package ru.runnerlite.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MySubscriptionsDto implements Serializable {
    private String teamsName; //имя команды
    private Integer teamsId; //для логотипа id команды
    private String nameSubscription; //наименование подписки
    private String descriptionSubscription; //описание подписки
}
