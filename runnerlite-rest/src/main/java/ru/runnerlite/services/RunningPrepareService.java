package ru.runnerlite.services;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.TeamsRunningCount;
import ru.runnerlite.entities.TeamsVolunteer;
import ru.runnerlite.entities.Volunteer;
import ru.runnerlite.entities.dto.RunningPrepareStatus;
import ru.runnerlite.entities.dto.TeamsRunningCountDto;
import ru.runnerlite.entities.dto.TeamsVolunteerDtoSimple;
import ru.runnerlite.entities.dto.VolunteerSimpleDto;
import ru.runnerlite.repositories.TeamsRunningCountRepository;
import ru.runnerlite.repositories.TeamsVolunteerRepository;
import ru.runnerlite.repositories.VolunteerRepository;

import java.sql.Timestamp;
import java.time.Instant;
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
	
	public List<RunningPrepareStatus> getStatus(Integer teamId, Integer count, String fromDate) {
		if (teamId == null || teamId < 1) {
			throw new IllegalArgumentException("Некорректный номер команды: teamId = " + teamId);
		}
		if (count == null || count < 1) {
			count = 3;
		}
		
		Instant dateFrom = (fromDate == null || fromDate.isEmpty()) ? Instant.now() :
			Instant.ofEpochMilli(Timestamp.valueOf(fromDate).getTime());
		List<RunningPrepareStatus> result = new ArrayList<>();
		List<TeamsRunningCount> runnings = teamsRunningCountRepository.findByTeamsIdAndRunningDateAfterOrderByIdAsc(teamId, dateFrom);
		for (TeamsRunningCount running : runnings) {
			RunningPrepareStatus rps = new RunningPrepareStatus();
			rps.setRunning(new TeamsRunningCountDto(running));
			
			List<TeamsVolunteer> tv = teamsVolunteerRepository.findByTeamsRunningCountId(running.getId());
			List<TeamsVolunteerDtoSimple> tvd = tv.stream()
				.map(TeamsVolunteerDtoSimple::new)
				.collect(Collectors.toList());
			rps.setNeedVolunteers(tvd);
			
			List<Volunteer> volunteers = volunteerRepository.findAllByTeamsRunningCountIdOrderByIdAsc(running.getId());
			List<VolunteerSimpleDto> v = volunteers.stream()
				.map(VolunteerSimpleDto::new)
				.collect(Collectors.toList());
			rps.setVolunteers(v);
			
			int needVolunteers = rps.getNeedVolunteers()
				.stream()
				.mapToInt(TeamsVolunteerDtoSimple::getNeedVolunteerQty)
				.sum();
			int volunteersCount = rps.getVolunteers().size();
			int percentage = needVolunteers == 0 ? 0 : volunteersCount * 100 / needVolunteers;
			rps.setRecruitmentPercentage(percentage);
			result.add(rps);
		}
	
		return result;
	}
}
