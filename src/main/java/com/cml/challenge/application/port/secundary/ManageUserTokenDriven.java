package com.cml.challenge.application.port.secundary;

import com.cml.challenge.domain.model.UserToken;

public interface ManageUserTokenDriven {

  UserToken findUserTokenByUserName(UserToken token);
  void saveUserToken(UserToken userToken);
  void deleteUserToken(UserToken userToken);
}
