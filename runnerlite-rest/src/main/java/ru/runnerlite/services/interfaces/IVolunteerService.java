package ru.runnerlite.services.interfaces;

import ru.runnerlite.entities.dto.VolunteerDto;

import java.util.List;

public interface IVolunteerService {
    VolunteerDto getLastHistoryVolunteering(String currentUserName);
}
