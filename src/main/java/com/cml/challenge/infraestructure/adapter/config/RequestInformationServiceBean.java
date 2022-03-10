package com.cml.challenge.infraestructure.adapter.config;

import com.cml.challenge.application.port.secundary.FindRequestInformation;
import com.cml.challenge.domain.service.RequestInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RequestInformationServiceBean {
    private final FindRequestInformation findRequestInformation;
    @Bean
    public RequestInformationService requestInformationService() {
        return new RequestInformationService(findRequestInformation);
    }
}
