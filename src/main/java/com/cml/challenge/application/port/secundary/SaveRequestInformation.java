package com.cml.challenge.application.port.secundary;

import com.cml.challenge.domain.model.RequestInformation;

public interface SaveRequestInformation {

  void saveRequestInfo(RequestInformation requestInformation);
}
