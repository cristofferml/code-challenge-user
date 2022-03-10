package com.cml.challenge.infraestructure.adapter.config;

import com.cml.challenge.application.port.secundary.CreateNewUser;
import com.cml.challenge.application.port.secundary.FindUserByUserName;
import com.cml.challenge.domain.service.SignUpUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserServiceConfigBean {

    private final CreateNewUser insertNewUser;

    private final FindUserByUserName findUserByUserName;

    @Bean
    public SignUpUserService signUpUserService() {
        return new SignUpUserService(insertNewUser, findUserByUserName);
    }
}
