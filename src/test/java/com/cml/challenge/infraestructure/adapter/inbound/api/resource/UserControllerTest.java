package com.cml.challenge.infraestructure.adapter.inbound.api.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cml.challenge.application.port.primary.SignUpNewUser;
import com.cml.challenge.infraestructure.adapter.inbound.api.model.UserSignUpRequest;
import com.cml.challenge.infraestructure.security.config.CustomLogoutHandler;
import com.cml.challenge.infraestructure.security.config.SecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@Import({SecurityConfig.class, TestUserTokenServiceConfigBean.class, CustomLogoutHandler.class})
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

  @MockBean
  private SignUpNewUser signUpNewUser;

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void givenValidSigUpParameter_whenASignUpAvailable_thenReturnOk() throws Exception {
    LocalDateTime starDate = LocalDateTime.parse("2021-12-29 13:00",
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    UserSignUpRequest signUpRequest = UserSignUpRequest.builder()
        .firstName("test1name")
        .email("mail@mail.com")
        .lastName("test1lastname")
        .password("password")
        .username("usertest")
        .build();


    mvc.perform(post("/user/sign-up")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsBytes(signUpRequest)))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void givenInvalidSigUpParameter_whenASignUpAvailable_thenReturnNok() throws Exception {
    LocalDateTime starDate = LocalDateTime.parse("2021-12-29 13:00",
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    UserSignUpRequest signUpRequest = UserSignUpRequest.builder()
        .firstName("test1name")
        .email("mailmail.com")
        .lastName("test1lastname")
        .password("pas")
        .build();


    mvc.perform(post("/user/sign-up")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsBytes(signUpRequest)))
        .andDo(print())
        .andExpect(status().is4xxClientError());
  }

}