package ru.runnerlite.security.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface IAuthorizationComponent {

    boolean mayUserChangeThisTeam(UserDetails userDetails,Integer teamId);
    boolean itsOwnVolunteerRequest(UserDetails userDetails, Integer idVolunteer);
    boolean itsOwnRunningRequest(UserDetails userDetails, Integer idRunnerCount);

}
