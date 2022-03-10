package com.cml.challenge.domain.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Pagination {

  private int page;

  private int pageSize;
}
