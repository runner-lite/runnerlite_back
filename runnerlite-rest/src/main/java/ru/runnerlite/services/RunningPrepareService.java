package ru.runnerlite.services;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.Team;
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
import ru.runnerlite.util.RunningStatus;

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
	
	public Integer getNewRunningNumber(Integer teamId) {
		return teamsRunningCountRepository.getNewRunningNumber(teamId).orElse(0) + 1;
	}
	
	public TeamsRunningCountDto save(TeamsRunningCountDto newRunningDto) {
		if (newRunningDto.getTeamId() == null || newRunningDto.getTeamId() <= 0) {
			throw new IllegalArgumentException("Недопустимый id команды: teamId = " + newRunningDto.getTeamId());
		}
		if (newRunningDto.getRunningDate() == null) {
			throw new IllegalArgumentException("Не указана дата начала забега");
		} else if (newRunningDto.getRunningDate().isBefore(Instant.now().plusSeconds(60 * 60 * 24))) {
			throw new IllegalArgumentException("Недопустимая дата забега! Забег можно запланировать на следующий или более поздний день " +
				"[" + newRunningDto.getRunningDate() + "]");
		}
		if (newRunningDto.getNumber() == null) {
			throw new IllegalArgumentException("Не указан номер забега.");
		} else if (newRunningDto.getNumber() <= 0) {
			throw new IllegalArgumentException("Недопустимый номер забега [" + newRunningDto.getNumber() + "]");
		}
		
		TeamsRunningCount newRunning = new TeamsRunningCount(
			newRunningDto.getId(),
			new Team(newRunningDto.getTeamId()),
			newRunningDto.getRunningDate(),
			newRunningDto.getNumber(),
			newRunningDto.getStatus() == null ? RunningStatus.PLANED.toString() : newRunningDto.getStatus()
		);
		TeamsRunningCountDto result = new TeamsRunningCountDto(teamsRunningCountRepository.save(newRunning));
		return result;
	}
}
