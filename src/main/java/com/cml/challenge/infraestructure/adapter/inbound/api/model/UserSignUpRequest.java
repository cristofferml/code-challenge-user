package com.cml.challenge.infraestructure.adapter.inbound.api.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpRequest {

  @Size(min=5, max = 15)
  @NotBlank
  private String username;

  @Size(min=5, max = 10)
  @NotBlank
  private String password;

  @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,3}$")
  @NotBlank
  private String email;

  @Size(min=2, max = 100)
  @NotBlank
  private String firstName;

  @Size(min=2, max = 100)
  @NotBlank
  private String lastName;
}
