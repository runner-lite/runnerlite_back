package ru.runnerlite.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.runnerlite.repositories.RunnerCountRepository;
import ru.runnerlite.repositories.SecUserRepository;
import ru.runnerlite.repositories.VolunteerRepository;
import ru.runnerlite.security.interfaces.IAuthorizationComponent;

import java.util.Optional;

//Класс для реализации логики проверок доступности методов
@Component("A")
public class AuthorizationComponent implements IAuthorizationComponent {

    private final SecUserRepository secUserRepository;

    private final VolunteerRepository volunteerRepository;

    private final RunnerCountRepository runnerCountRepository;
    @Autowired
    public AuthorizationComponent(SecUserRepository secUserRepository,
                                  VolunteerRepository volunteerRepository,
                                  RunnerCountRepository runnerCountRepository) {
        this.secUserRepository = secUserRepository;
        this.volunteerRepository=volunteerRepository;
        this.runnerCountRepository = runnerCountRepository;
    }

    //Метод проверяет является ли сотрудник участником той же команды по которой совершает операцию
    @Override
    public boolean mayUserChangeThisTeam(UserDetails userDetails, Integer teamId) {
        Optional<Integer> userTeamId =secUserRepository.findTeamByUsername(userDetails.getUsername());
        if(userTeamId.isPresent()){
            return userTeamId.get().equals(teamId);
        }
        else {
            return false;
        }
    }

    //Метод проверят пренадлежит ли запись о волонтерстве автору запроса.
    @Override
    public boolean itsOwnVolunteerRequest(UserDetails userDetails, Integer idVolunteer){
        Integer userId = secUserRepository.findById(userDetails.getUsername());
        Optional<Integer> idInRequest = volunteerRepository.findSecUserIdByVolunteersId(idVolunteer);
        return userId.equals(idInRequest.get());
    }

    //Метод проверят пренадлежит ли запись об участии в забеге автору запроса.
    @Override
    public boolean itsOwnRunningRequest(UserDetails userDetails, Integer idRunnerCount) {
        Integer userId = secUserRepository.findById(userDetails.getUsername());
        Integer idInRequest = runnerCountRepository.findSecUserId(idRunnerCount);
        return userId.equals(idInRequest);
    }
}
