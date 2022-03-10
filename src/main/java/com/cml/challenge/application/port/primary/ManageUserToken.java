package com.cml.challenge.application.port.primary;

import com.cml.challenge.domain.model.UserToken;

public interface ManageUserToken {

  UserToken getUserTokenByUserName(UserToken token);
  void associateUserToken(UserToken userToken);
  void removeUserToken(UserToken userToken);
}
