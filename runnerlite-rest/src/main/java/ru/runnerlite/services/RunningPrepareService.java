package ru.runnerlite.services;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.TeamsRunningCount;
import ru.runnerlite.entities.TeamsVolunteer;
import ru.runnerlite.entities.Volunteer;
import ru.runnerlite.entities.dto.RunningPrepareStatus;
import ru.runnerlite.entities.dto.TeamsRunningCountDto;
import ru.runnerlite.entities.dto.TeamsVolunteerDto;
import ru.runnerlite.entities.dto.VolunteerSimpleDto;
import ru.runnerlite.repositories.TeamsRunningCountRepository;
import ru.runnerlite.repositories.TeamsVolunteerRepository;
import ru.runnerlite.repositories.VolunteerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class RunningPrepareService {
	
	private TeamsRunningCountRepository teamsRunningCountRepository;
	private TeamsVolunteerRepository teamsVolunteerRepository;
	private VolunteerRepository volunteerRepository;
	
	@Autowired
	public RunningPrepareService(TeamsRunningCountRepository teamsRunningCountRepository, TeamsVolunteerRepository teamsVolunteerRepository, VolunteerRepository volunteerRepository) {
		this.teamsRunningCountRepository = teamsRunningCountRepository;
		this.teamsVolunteerRepository = teamsVolunteerRepository;
		this.volunteerRepository = volunteerRepository;
	}
	
	public List<RunningPrepareStatus> getStatus(Integer teamId, Integer count) {
		if (teamId == null || teamId < 1) {
			throw new IllegalArgumentException("Некорректный номер команды: teamId = " + teamId);
		}
		if (count == null || count < 1) {
			count = 3;
		}
		List<RunningPrepareStatus> result = new ArrayList<>();
		List<TeamsRunningCount> runnings = teamsRunningCountRepository.findByTeamsIdOrderByIdAsc(teamId);
		for (TeamsRunningCount running : runnings) {
			RunningPrepareStatus rps = new RunningPrepareStatus();
			rps.setRunning(new TeamsRunningCountDto(running));

//			Integer needVolunteers = teamsVolunteerRepository.getNeedVolunteersCount(running.getId());
//			Integer volunteersCount = volunteerRepository.getVolunteersCount(running.getId());
			
			List<TeamsVolunteer> tv = teamsVolunteerRepository.findByTeamsRunningCountId(running.getId());
			List<TeamsVolunteerDto> tvd = tv.stream()
				.map(TeamsVolunteerDto::new)
				.collect(Collectors.toList());
			rps.setNeedVolunteers(tvd);
			
			List<Volunteer> volunteers = volunteerRepository.findAllByTeamsRunningCountIdOrderByIdAsc(running.getId());
			List<VolunteerSimpleDto> v = volunteers.stream()
				.map(VolunteerSimpleDto::new)
				.collect(Collectors.toList());
			rps.setVolunteers(v);
//			Integer percentage = volunteersCount / needVolunteers * 100;
//			RunningPrepareStatus rps = new RunningPrepareStatus(new TeamsRunningCountDto(running), percentage);
			result.add(rps);
		}
	
		return result;
	}
}
