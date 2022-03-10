package com.cml.challenge.application.port.secundary;

import com.cml.challenge.domain.model.User;

public interface CreateNewUser {

  void insertNewUser(User user);
}
