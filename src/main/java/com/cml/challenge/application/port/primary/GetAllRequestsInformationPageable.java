package com.cml.challenge.application.port.primary;

import com.cml.challenge.domain.model.Pagination;
import com.cml.challenge.domain.model.RequestsInformation;

public interface GetAllRequestsInformationPageable {

    RequestsInformation getAllRequestInformationPageable(Pagination pagination);
}
