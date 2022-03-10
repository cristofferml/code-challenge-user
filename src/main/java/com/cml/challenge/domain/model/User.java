package com.cml.challenge.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;
}
