package ru.runnerlite.services.interfaces;

import ru.runnerlite.entities.dto.MySubscriptionsDto;

import java.util.List;

public interface IMySubscriptionService {
    List<MySubscriptionsDto> findMySubscriptions(String currentUserName);
}
