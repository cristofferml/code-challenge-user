package com.cml.challenge.application.port.secundary;

import com.cml.challenge.domain.model.Pagination;
import com.cml.challenge.domain.model.RequestsInformation;

public interface FindRequestInformation {
  RequestsInformation findAllRequestInformationPageable(Pagination pagination);
}
