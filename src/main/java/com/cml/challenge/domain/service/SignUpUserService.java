package com.cml.challenge.domain.service;

import com.cml.challenge.application.exception.UserNameExistException;
import com.cml.challenge.application.port.primary.SignUpNewUser;
import com.cml.challenge.application.port.secundary.CreateNewUser;
import com.cml.challenge.application.port.secundary.FindUserByUserName;
import com.cml.challenge.domain.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SignUpUserService implements SignUpNewUser {

    private final CreateNewUser createNewUser;

    private final FindUserByUserName findUserByUserName;

    @Override
    public void signUpNewUser(User user) {
        if(findUserByUserName.getUserByUserName(user.getUsername()) != null) {
            throw new UserNameExistException();
        }
        createNewUser.insertNewUser(user);
    }
}
