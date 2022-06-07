package ru.runnerlite.services.interfaces;

import ru.runnerlite.entities.dto.TeamRunVolunteerQtyDto;
import ru.runnerlite.entities.dto.VolunteerDto;

import java.util.List;

public interface IVolunteerService {
    VolunteerDto getLastHistoryVolunteering(String currentUserName);

    List<TeamRunVolunteerQtyDto> getNeedTeamRunVolunteerQty(Integer teamId);

    List<TeamRunVolunteerQtyDto> putNeedTeamRunVolunteerQty(TeamRunVolunteerQtyDto teamRunVolunteerQtyDto);
}
