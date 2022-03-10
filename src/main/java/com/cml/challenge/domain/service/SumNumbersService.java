package com.cml.challenge.domain.service;

import com.cml.challenge.application.port.primary.SumNumbers;

public class SumNumbersService implements SumNumbers {
  @Override
  public Double sum(double fistNumber, double secondNumber) {
    return fistNumber + secondNumber;
  }
}
