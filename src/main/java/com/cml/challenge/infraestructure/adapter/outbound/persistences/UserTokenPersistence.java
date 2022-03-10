package com.cml.challenge.infraestructure.adapter.outbound.persistences;

import com.cml.challenge.application.port.secundary.ManageUserTokenDriven;
import com.cml.challenge.domain.model.UserToken;
import com.cml.challenge.infraestructure.adapter.outbound.persistences.entities.UserTokenEntity;
import com.cml.challenge.infraestructure.adapter.outbound.persistences.respository.UserTokenRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserTokenPersistence implements ManageUserTokenDriven {

  private final UserTokenRepository userTokenRepository;

  @Override
  public UserToken findUserTokenByUserName(UserToken userToken) {
    Optional<UserTokenEntity> entity = userTokenRepository.findByUsername(userToken.getUsername());

    UserToken responseUserToken = entity.map(
        v -> UserToken.builder()
            .token(v.getToken())
            .username(v.getUsername())
            .build()
    ).orElse(null);
    return responseUserToken;
  }

  @Override
  public void saveUserToken(UserToken userToken) {
    UserTokenEntity entity = UserTokenEntity.builder()
        .token(userToken.getToken())
        .username(userToken.getUsername())
        .build();
    userTokenRepository.save(entity);

  }

  @Override
  public void deleteUserToken(UserToken userToken) {
    this.userTokenRepository.findByUsername(userToken.getUsername())
        .ifPresent(
            v -> userTokenRepository.delete(v));
  }
}
