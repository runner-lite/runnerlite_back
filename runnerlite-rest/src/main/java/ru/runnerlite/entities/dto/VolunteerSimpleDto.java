package ru.runnerlite.entities.dto;

import lombok.Getter;
import lombok.Setter;
import ru.runnerlite.entities.Volunteer;

import java.io.Serializable;

@Getter
@Setter
public class VolunteerSimpleDto implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer status; // Статус запроса (0 - запрос /1 - принято/ 2 - отказано)
    private String position;
    private Integer runningId;
    
    public VolunteerSimpleDto() {
    }
    
    public VolunteerSimpleDto(Integer id, Integer userId, Integer status, String position, Integer runningId) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.position = position;
        this.runningId = runningId;
    }
    
    public VolunteerSimpleDto(Volunteer volunteer) {
        this.id = volunteer.getId();
        this.userId = volunteer.getSecUsers().getId();
        this.status = volunteer.getStatus();
        this.position = volunteer.getRefVolunteersPosition().getName();
        this.runningId = volunteer.getTeamsRunningCount().getId();
    }
}
