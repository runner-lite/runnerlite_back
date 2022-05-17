package ru.runnerlite.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
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
        List<VolunteerDto> volunteerDto = volunteerRepository.findVolunteerByUserName(currentUserName, PageRequest.of(0,1));
        List<String> list = volunteerRepository.historicalListVolunteerism(currentUserName);
        Long countHistoricalVolunteerism = volunteerRepository.historicalVolunteerismCount(currentUserName);
        if (volunteerDto == null) {
            throw new IllegalArgumentException("Пользователь с id = " + currentUserName + " не найден.");
        }
        return convert(volunteerDto.get(0), list, countHistoricalVolunteerism);
    }

    public VolunteerDto convert(VolunteerDto volunteer, List<String> list, Long countHistoricalVolunteerism) {
        return new VolunteerDto(
                volunteer.getId(),
                volunteer.getUserId(),
                volunteer.getRunningDate(),
                volunteer.getRunningNumber(),
                volunteer.getPositionName(),
                volunteer.getPositionDescription(),
                volunteer.getTeamsName(),
                volunteer.getTeamsId(),
                countHistoricalVolunteerism,
                list
        );
    }

}
