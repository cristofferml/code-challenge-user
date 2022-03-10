package com.cml.challenge.infraestructure.adapter.outbound.persistences;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cml.challenge.domain.model.User;
import com.cml.challenge.infraestructure.adapter.outbound.persistences.entities.UserEntity;
import com.cml.challenge.infraestructure.adapter.outbound.persistences.respository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UserNamePersistenceTest {
  @Mock
  private UserRepository userRepository;

  @Mock
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @InjectMocks
  private UserNamePersistence userNamePersistence;

  @Test
  void insertNewUserOk () {
    User user = User.builder()
        .firstName("test1name")
        .email("mail@mail.com")
        .lastName("test1lastname")
        .password("password")
        .username("usertest")
        .build();

    when(bCryptPasswordEncoder.encode(anyString())).thenReturn("secretPass");

    UserEntity userEntity = UserEntity.builder().username("usertest").build();
    when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

    ArgumentCaptor<UserEntity> argument =
        ArgumentCaptor.forClass(UserEntity.class);

    userNamePersistence.insertNewUser(user);
    verify(userRepository).save(argument.capture());
    assertEquals(user.getUsername(),
        argument.getValue().getUsername());

  }
}