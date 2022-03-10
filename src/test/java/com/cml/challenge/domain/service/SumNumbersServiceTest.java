package com.cml.challenge.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class SumNumbersServiceTest {

  @InjectMocks
  private SumNumbersService sumNumbersService;

  @Test
  void sumNumberOk(){
    assertEquals(3.0, sumNumbersService.sum(1.0, 2.0));
    assertEquals(3.0, sumNumbersService.sum(1.5, 1.5));
  }

  @Test
  void sumNumberNOk(){
    assertNotEquals(3.0, sumNumbersService.sum(2.0, 2.0));
    assertNotEquals(3.0, sumNumbersService.sum(2.5, 1.5));
  }

}