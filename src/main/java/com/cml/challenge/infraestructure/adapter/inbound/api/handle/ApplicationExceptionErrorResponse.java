package com.cml.challenge.infraestructure.adapter.inbound.api.handle;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ApplicationExceptionErrorResponse {

  private LocalDateTime timestamp;

  private String message;

  private Map<String, String> detail;

  private int code;
}
