package ru.runnerlite.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.runnerlite.repositories.SecUserRepository;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UserAuthService implements UserDetailsService {

    private final SecUserRepository secUserRepository;

    @Autowired
    public UserAuthService(SecUserRepository secUserRepository) {
        this.secUserRepository = secUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return secUserRepository.findByUsername(username)
                .map(secUser -> new User(secUser.getEmail(),
                                          secUser.getPassword(),
                                            Collections.singleton(new SimpleGrantedAuthority("ADMIN"))
//                                          secUser.getSecUsergroupsMember().stream()
//                                                  .map(secUsergroupsMember -> new SimpleGrantedAuthority("ADMIN"))
//                                                  .collect(Collectors.toList())
                        )
                )
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}
