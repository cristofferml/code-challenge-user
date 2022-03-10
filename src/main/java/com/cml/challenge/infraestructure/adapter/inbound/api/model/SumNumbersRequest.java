package com.cml.challenge.infraestructure.adapter.inbound.api.model;

import javax.validation.constraints.NotNull;
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
public class SumNumbersRequest {
  @NotNull
  private Double firstNumber;

  @NotNull
  private Double secondNumber;
}
