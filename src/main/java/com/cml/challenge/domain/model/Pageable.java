package com.cml.challenge.domain.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Pageable {
  private long totalElements;

  private int currentPage;

  private int totalPages;
}
