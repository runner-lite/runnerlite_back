package ru.runnerlite.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.runnerlite.repositories.SecUserRepository;
import ru.runnerlite.security.interfaces.IAuthorizationComponent;

import java.util.Optional;

//Класс для реализации логики проверок доступности методов
@Component("A")
public class AuthorizationComponent implements IAuthorizationComponent {

    private final SecUserRepository secUserRepository;

    @Autowired
    public AuthorizationComponent(SecUserRepository secUserRepository) {
        this.secUserRepository = secUserRepository;
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
}
