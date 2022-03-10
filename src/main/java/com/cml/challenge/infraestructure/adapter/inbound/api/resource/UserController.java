package com.cml.challenge.infraestructure.adapter.inbound.api.resource;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.cml.challenge.application.port.primary.SignUpNewUser;
import com.cml.challenge.domain.model.User;
import com.cml.challenge.infraestructure.adapter.inbound.api.model.UserSignUpRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@Slf4j
@RequiredArgsConstructor
@Validated
public class UserController {

    private final SignUpNewUser signUpNewUser;

    @PostMapping(value = "/sign-up",
        produces = APPLICATION_JSON_VALUE,
        consumes = APPLICATION_JSON_VALUE)
    public void newReservation(@Valid @RequestBody UserSignUpRequest signUpRequest) {
        log.info("user info {}", signUpRequest);

        User user = User.builder()
            .email(signUpRequest.getEmail())
            .firstName(signUpRequest.getFirstName())
            .lastName(signUpRequest.getLastName())
            .password(signUpRequest.getPassword())
            .username(signUpRequest.getUsername())
            .build();
        signUpNewUser.signUpNewUser(user);
    }

}
