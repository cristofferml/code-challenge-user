package com.cml.challenge.infraestructure.adapter.inbound.api.resource;

import com.cml.challenge.application.port.secundary.ManageUserTokenDriven;
import com.cml.challenge.domain.service.ManageUserTokenService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class TestUserTokenServiceConfigBean {

  @MockBean
  private ManageUserTokenDriven manageUserTokenDriven;

  @MockBean
  private ManageUserTokenService signUpUserService;

  @MockBean
  private RabbitTemplate rabbitTemplate;
}
