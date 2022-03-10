package com.cml.challenge.infraestructure.adapter.inbound.api.model;

import com.cml.challenge.domain.model.RequestInformation;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestsInformationResponse {

  private Page page;
  private List<RequestInformation> requestInformation;
}
