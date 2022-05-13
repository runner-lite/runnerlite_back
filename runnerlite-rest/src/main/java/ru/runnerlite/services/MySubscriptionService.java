package ru.runnerlite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.dto.MySubscriptionsDto;
import ru.runnerlite.repositories.MailingListRepository;
import ru.runnerlite.services.interfaces.IMySubscriptionService;

import java.util.List;

@Service
public class MySubscriptionService implements IMySubscriptionService {

    MailingListRepository mailingListRepository;

    @Autowired
    public MySubscriptionService(MailingListRepository mailingListRepository) {
        this.mailingListRepository = mailingListRepository;
    }

    @Override
    public List<MySubscriptionsDto> findMySubscriptions(String currentUserName) {

        List<MySubscriptionsDto> mySubscriptionsDto = mailingListRepository.findMySubscriptions(currentUserName);
        if (mySubscriptionsDto == null) {
            throw new IllegalArgumentException("Пользователь с id = " + currentUserName + " не найден.");
        }
        return mySubscriptionsDto;
    }
}
