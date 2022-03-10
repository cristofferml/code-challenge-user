package com.cml.challenge.domain.service;

import com.cml.challenge.application.port.primary.GetAllRequestsInformationPageable;
import com.cml.challenge.application.port.secundary.FindRequestInformation;
import com.cml.challenge.domain.model.Pagination;
import com.cml.challenge.domain.model.RequestsInformation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RequestInformationService implements GetAllRequestsInformationPageable {

  private final FindRequestInformation findRequestInformation;
  @Override
  public RequestsInformation getAllRequestInformationPageable(Pagination pagination) {
    return findRequestInformation.findAllRequestInformationPageable(pagination);
  }
}
