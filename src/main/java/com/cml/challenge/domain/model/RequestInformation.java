package com.cml.challenge.domain.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestInformation {

  private String username;

  private String urlRequest;

  private String ip;

  private LocalDateTime date;
}
