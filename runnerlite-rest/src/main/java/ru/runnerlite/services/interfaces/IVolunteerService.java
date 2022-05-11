package ru.runnerlite.services.interfaces;

import ru.runnerlite.entities.dto.VolunteerDto;

public interface IVolunteerService {
    VolunteerDto getLastHistoryVolunteering(Integer userId);
}
