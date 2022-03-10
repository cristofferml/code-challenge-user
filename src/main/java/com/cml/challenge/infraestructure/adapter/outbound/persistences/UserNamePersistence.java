package com.cml.challenge.infraestructure.adapter.outbound.persistences;

import com.cml.challenge.application.port.secundary.CreateNewUser;
import com.cml.challenge.application.port.secundary.FindUserByUserName;
import com.cml.challenge.domain.model.User;
import com.cml.challenge.infraestructure.adapter.outbound.persistences.entities.UserEntity;
import com.cml.challenge.infraestructure.adapter.outbound.persistences.respository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserNamePersistence implements CreateNewUser, FindUserByUserName {

  private final UserRepository userRepository;

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public void insertNewUser(User user) {

    UserEntity userEntity = UserEntity.builder()
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .username(user.getUsername())
        .password(bCryptPasswordEncoder.encode(user.getPassword()))
        .build();
    userRepository.save(userEntity);

  }

  @Override
  public User getUserByUserName(String username) {

    Optional<UserEntity> userEntity = Optional.ofNullable(userRepository.findByUsername(username));
    User user = userEntity.map(
        u -> User.builder()
            .firstName(u.getFirstName())
            .lastName(u.getLastName())
            .email(u.getEmail())
            .username(u.getUsername())
            .password(u.getPassword())
            .build()
    ).orElse(null);
    return user;
  }
}
