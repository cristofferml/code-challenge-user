package com.cml.challenge.infraestructure.adapter.inbound.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenInfo implements Serializable {
  @JsonProperty("user")
  private String username;

  @JsonProperty("token")
  private String token;

}
