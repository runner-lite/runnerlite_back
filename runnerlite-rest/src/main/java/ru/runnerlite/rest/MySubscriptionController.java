package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.runnerlite.entities.dto.MySubscriptionsDto;
import ru.runnerlite.services.interfaces.IMySubscriptionService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/subscription")
public class MySubscriptionController {

    IMySubscriptionService mySubscriptionService;

    @Autowired
    public MySubscriptionController(IMySubscriptionService mySubscriptionService) {
        this.mySubscriptionService = mySubscriptionService;
    }

    @GetMapping("/get")
    public List<MySubscriptionsDto> getMySubscriptions(Principal principal) {
        String currentUserName = principal.getName();
        return mySubscriptionService.findMySubscriptions(currentUserName);
    }
}
