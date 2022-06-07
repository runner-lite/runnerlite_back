package ru.runnerlite.services.interfaces;

import ru.runnerlite.entities.TeamsRunningCount;
import ru.runnerlite.entities.Volunteer;

public interface ILetterService {
    void sendVolunteerRequestLetter(Volunteer volunteer, TeamsRunningCount teamsRunningCount);
    void sendVolunteerAcceptLetter(Integer volunteerId,String status);
    void sendRunningResultsToRunner(Integer runningNumber,Integer teamId);
}
