package com.cml.challenge.domain.model;

import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class RequestsInformation extends Pageable{

  private List<RequestInformation> requestsInformation;


}
