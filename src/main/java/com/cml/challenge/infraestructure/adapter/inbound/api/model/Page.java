package com.cml.challenge.infraestructure.adapter.inbound.api.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Page {
  private int number;
  private int size;
  private int totalPages;
  private long totalElements;
}
