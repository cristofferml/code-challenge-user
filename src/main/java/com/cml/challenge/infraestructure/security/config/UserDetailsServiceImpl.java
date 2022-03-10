package com.cml.challenge.infraestructure.security.config;

import com.cml.challenge.application.port.secundary.FindUserByUserName;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final FindUserByUserName findUserByUserName;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    com.cml.challenge.domain.model.User userDomain = findUserByUserName.getUserByUserName(username);

    if (userDomain == null) {
      throw new UsernameNotFoundException(username);
    }
    return new User(username, userDomain.getPassword(),
        Collections.emptyList());
  }
}
