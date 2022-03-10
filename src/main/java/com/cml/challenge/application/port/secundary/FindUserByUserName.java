package com.cml.challenge.application.port.secundary;

import com.cml.challenge.domain.model.User;

public interface FindUserByUserName {

  User getUserByUserName(String username);
}
