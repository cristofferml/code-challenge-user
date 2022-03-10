package com.cml.challenge.infraestructure.adapter.inbound.api.resource;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.cml.challenge.application.port.primary.GetAllRequestsInformationPageable;
import com.cml.challenge.domain.model.Pagination;
import com.cml.challenge.domain.model.RequestsInformation;
import com.cml.challenge.infraestructure.adapter.inbound.api.model.Page;
import com.cml.challenge.infraestructure.adapter.inbound.api.model.RequestsInformationResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("request-info")
@Slf4j
@RequiredArgsConstructor
@Validated
public class RequestInfoController {

  private final GetAllRequestsInformationPageable getAllRequestsInformationPageable;

  @Operation(summary = "Get list of Request transaction info. IMPORTANT!: Sorter functionality is not implemented yet")
  @GetMapping(value = "/page",
      produces = APPLICATION_JSON_VALUE)
  public RequestsInformationResponse getAllRequestInformationPageable(
      @PageableDefault(size = 30, page = 1) Pageable pageable) {
    log.info("Get all request information, page {}, pageSize",
        pageable.getPageNumber(), pageable.getPageSize());

    Pagination pagination = Pagination.builder()
        .page(pageable.getPageNumber())
        .pageSize(pageable.getPageSize())
        .build();

    RequestsInformation result =
        getAllRequestsInformationPageable.getAllRequestInformationPageable(pagination);

    RequestsInformationResponse responseResult = RequestsInformationResponse.builder()
        .requestInformation(result.getRequestsInformation())
        .page(Page.builder()
            .number(result.getCurrentPage())
            .totalPages(result.getTotalPages())
            .totalElements(result.getTotalElements())
            .size(pageable.getPageSize())
            .build()
        )
        .build();

    log.info("resultado {}", responseResult);
    return responseResult;
  }

}
