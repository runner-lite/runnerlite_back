package ru.runnerlite.services;

import org.springframework.stereotype.Service;
import ru.runnerlite.entities.Volunteer;
import ru.runnerlite.entities.dto.VolunteerDto;
import ru.runnerlite.repositories.VolunteerRepository;
import ru.runnerlite.services.interfaces.IVolunteerService;

import java.util.List;

@Service
public class VolunteerService<list> implements IVolunteerService {

    private final VolunteerRepository volunteerRepository;

    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public VolunteerDto getLastHistoryVolunteering(String currentUserName) {

        VolunteerDto volunteerDto = volunteerRepository.findVolunteerByUserName(currentUserName);
        List<String> list = volunteerRepository.historicalistVolunteerism(currentUserName);
        if (volunteerDto == null) {
            throw new IllegalArgumentException("Пользователь с id = " + currentUserName + " не найден.");
        }
        return convert(volunteerDto, list);
    }

    public VolunteerDto convert(VolunteerDto volunteer, List<String> list) {
        return new VolunteerDto(
                volunteer.getId(),
                volunteer.getUserId(),
                volunteer.getRunningDate(),
                volunteer.getRunningNumber(),
                volunteer.getPositionName(),
                volunteer.getPositionDescription(),
                volunteer.getTeamsName(),
                volunteer.getTeamsId(),
                volunteer.getRunningNumberСount(),
                list
        );
    }

}
