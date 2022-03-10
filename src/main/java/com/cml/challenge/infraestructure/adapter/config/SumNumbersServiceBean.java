package com.cml.challenge.infraestructure.adapter.config;

import com.cml.challenge.domain.service.SumNumbersService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SumNumbersServiceBean {

    @Bean
    public SumNumbersService sumNumbersService() {
        return new SumNumbersService();
    }
}
