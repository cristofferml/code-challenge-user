package com.cml.challenge.domain.service;

import com.cml.challenge.application.port.primary.ManageUserToken;
import com.cml.challenge.application.port.secundary.ManageUserTokenDriven;
import com.cml.challenge.domain.model.UserToken;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ManageUserTokenService implements ManageUserToken {

  private final ManageUserTokenDriven manageUserTokenDriven;

  @Override
  public UserToken getUserTokenByUserName(UserToken token) {
    return manageUserTokenDriven.findUserTokenByUserName(token);
  }

  @Override
  public void associateUserToken(UserToken userToken) {
    if(manageUserTokenDriven.findUserTokenByUserName(userToken) != null) {
      manageUserTokenDriven.deleteUserToken(userToken);
    }
    manageUserTokenDriven.saveUserToken(userToken);
  }

  @Override
  public void removeUserToken(UserToken userToken) {
    manageUserTokenDriven.deleteUserToken(userToken);
  }
}
