package ru.runnerlite.services;

import org.springframework.stereotype.Service;
import ru.runnerlite.entities.Volunteer;
import ru.runnerlite.entities.dto.VolunteerDto;
import ru.runnerlite.repositories.VolunteerRepository;
import ru.runnerlite.services.interfaces.IVolunteerService;

import java.util.List;

@Service
public class VolunteerService implements IVolunteerService {

    private final VolunteerRepository volunteerRepository;

    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public VolunteerDto getLastHistoryVolunteering(Integer userId) {
        Volunteer volunteer = volunteerRepository.findFirstBySecUsers_IdOrderByIdDesc(userId);
        Integer findCountVolunteerPart = volunteerRepository.countBySecUsers_Id(userId);
        List<String> findCountVolunteerPartName = volunteerRepository.findVolunteerPositionName(userId);
        if (volunteer == null) {
            throw new IllegalArgumentException("Пользователь с id = " + userId + " не найден.");
        }
        return convert(volunteer, findCountVolunteerPart, findCountVolunteerPartName);
    }

    public VolunteerDto convert(Volunteer volunteer, Integer findCountVolunteerPart, List<String> findCountVolunteerPartName) {
        return new VolunteerDto(
                volunteer.getId(),
                volunteer.getSecUsers().getId(),
                volunteer.getTeamsRunningCount().getRunningDate(),
                volunteer.getTeamsRunningCount().getNumber(),
                volunteer.getRefVolunteersPosition().getName(),
                volunteer.getRefVolunteersPosition().getDescription(),
                volunteer.getTeamsRunningCount().getTeams().getName(),
                volunteer.getTeamsRunningCount().getTeams().getId(),
                findCountVolunteerPart,
                findCountVolunteerPartName
        );
    }

}
