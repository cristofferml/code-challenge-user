package com.cml.challenge.infraestructure.adapter.config;

import com.cml.challenge.application.port.secundary.ManageUserTokenDriven;
import com.cml.challenge.domain.service.ManageUserTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserTokenServiceConfigBean {

    private final ManageUserTokenDriven manageUserTokenDriven;

    @Bean
    public ManageUserTokenService manageUserTokenService() {
        return new ManageUserTokenService(manageUserTokenDriven);
    }
}
