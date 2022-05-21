package ru.runnerlite.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.runnerlite.entities.RefVolunteersPosition;
import ru.runnerlite.entities.Team;
import ru.runnerlite.entities.TeamsVolunteerTemplate;
import ru.runnerlite.entities.dto.TeamRunVolunteerQtyDto;
import ru.runnerlite.entities.dto.VolunteerDto;
import ru.runnerlite.repositories.RefVolunteersPositionRepository;
import ru.runnerlite.repositories.TeamRepository;
import ru.runnerlite.repositories.TeamsVolunteerTemplateRepository;
import ru.runnerlite.repositories.VolunteerRepository;
import ru.runnerlite.services.interfaces.IVolunteerService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VolunteerService<list> implements IVolunteerService {

    private final VolunteerRepository volunteerRepository;
    private final RefVolunteersPositionRepository  refVolunteersPositionRepository;
    private final TeamRepository teamRepository;

    private final TeamsVolunteerTemplateRepository teamsVolunteerTemplateRepository;

    public VolunteerService(VolunteerRepository volunteerRepository,
                            RefVolunteersPositionRepository refVolunteersPositionRepository,
                            TeamRepository teamRepository,
                            TeamsVolunteerTemplateRepository teamsVolunteerTemplateRepository) {
        this.volunteerRepository = volunteerRepository;
        this.refVolunteersPositionRepository = refVolunteersPositionRepository;
        this.teamRepository = teamRepository;
        this.teamsVolunteerTemplateRepository = teamsVolunteerTemplateRepository;
    }

    @Override
    public VolunteerDto getLastHistoryVolunteering(String currentUserName) {
        List<VolunteerDto> volunteerDto = volunteerRepository.findVolunteerByUserName(currentUserName, PageRequest.of(0,1));
        List<String> list = volunteerRepository.historicalListVolunteerism(currentUserName);
        Integer countHistoricalVolunteerism = volunteerRepository.historicalVolunteerismCount(currentUserName);
        if (volunteerDto.size() == 0) {
            return null;
        }
        return convert(volunteerDto.get(0), list, countHistoricalVolunteerism);
    }

    //Метод для получения шаблона волонтерства команды
    @Override
    public List<TeamRunVolunteerQtyDto> getNeedTeamRunVolunteerQty(Integer teamId) {
        List<TeamRunVolunteerQtyDto> outList= volunteerRepository.getNeedTeamRunVolunteerQty(teamId);
        if(outList.isEmpty()){
            throw new IllegalArgumentException("Для команды с id = " + teamId + " шаблон необходимых волонтеров не найден.");
        }
        return outList;
    }

    //Метод добавления записи в шаблон волонтерства
    @Override
    @Transactional
    public List<TeamRunVolunteerQtyDto> putNeedTeamRunVolunteerQty(TeamRunVolunteerQtyDto teamRunVolunteerQtyDto) {
        //ищем уже имеющиеся записи что бы не делать дубли
        TeamsVolunteerTemplate teamsVolunteerTemplate = teamsVolunteerTemplateRepository.
                findTemplateInTeamByPosition(teamRunVolunteerQtyDto.getTeamId(),teamRunVolunteerQtyDto.getPositionId());
        //если не нашли делаем новую запись
        if(teamsVolunteerTemplate == null){
            RefVolunteersPosition refVolunteersPosition = refVolunteersPositionRepository.getById(teamRunVolunteerQtyDto.getPositionId());
            Team team=teamRepository.getById(teamRunVolunteerQtyDto.getTeamId());
            teamsVolunteerTemplate = new TeamsVolunteerTemplate(null,team,refVolunteersPosition, teamRunVolunteerQtyDto.getQty());
        }
        //если нашли меняем количество у уже имеющейся
        else{
            teamsVolunteerTemplate.setQty(teamRunVolunteerQtyDto.getQty());
        }
        teamsVolunteerTemplateRepository.save(teamsVolunteerTemplate);
        return getNeedTeamRunVolunteerQty(teamRunVolunteerQtyDto.getTeamId());
    }

    public VolunteerDto convert(VolunteerDto volunteer, List<String> list, Integer countHistoricalVolunteerism) {
        return new VolunteerDto(
                volunteer.getId(),
                volunteer.getUserId(),
                volunteer.getFullName(),
                volunteer.getStatus(),
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
