package com.cml.challenge.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserToken {
    private String username;

    private String token;
}
