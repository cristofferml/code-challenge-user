package com.cml.challenge.application.exception;

public class UserNameExistException  extends RuntimeException {

  public UserNameExistException() {
    super("The username is already used");
  }
}
