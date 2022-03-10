package com.cml.challenge.infraestructure.adapter.outbound.persistences;

import com.cml.challenge.application.port.secundary.FindRequestInformation;
import com.cml.challenge.application.port.secundary.SaveRequestInformation;
import com.cml.challenge.domain.model.Pagination;
import com.cml.challenge.domain.model.RequestInformation;
import com.cml.challenge.domain.model.RequestsInformation;
import com.cml.challenge.infraestructure.adapter.inbound.api.model.InterceptorInformation;
import com.cml.challenge.infraestructure.adapter.outbound.persistences.entities.RequestInformationEntity;
import com.cml.challenge.infraestructure.adapter.outbound.persistences.respository.RequestInformationRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestInformationPersistence implements SaveRequestInformation,
    FindRequestInformation {

  private final RequestInformationRepository requestInformationRepository;

  @Override
  public void saveRequestInfo(RequestInformation requestInformation) {
    RequestInformationEntity entity = RequestInformationEntity.builder()
        .urlRequest(requestInformation.getUrlRequest())
        .date(requestInformation.getDate())
        .ip(requestInformation.getIp())
        .username(requestInformation.getUsername())
        .build();

    requestInformationRepository.save(entity);
  }

  @RabbitListener(queues = "request-interceptor")
  public void listeningNewRequest(InterceptorInformation in) {
    if(in == null) {
      throw new IllegalArgumentException("Request Information shouldn't be null");
    }
    RequestInformation rInformation = RequestInformation.builder()
        .urlRequest(in.getUrlRequest())
        .ip(in.getIp())
        .date(in.getDate())
        .username(in.getUsername())
        .build();
    this.saveRequestInfo(rInformation);
  }

  @Override
  public RequestsInformation findAllRequestInformationPageable(Pagination pagination) {
    Pageable pageable = PageRequest.of(pagination.getPage() - 1, pagination.getPageSize());
    Page<RequestInformationEntity> info = requestInformationRepository.findAll(pageable);
    List<RequestInformationEntity> request = info.getContent();
    List<RequestInformation> requestsInformation = request.stream()
        .map(
            v -> RequestInformation.builder()
            .urlRequest(v.getUrlRequest())
            .ip(v.getIp())
            .date(v.getDate())
            .username(v.getUsername())
            .build()
        ).collect(Collectors.toList());
    RequestsInformation requestsInfo
        = RequestsInformation.builder()
        .currentPage(pagination.getPage())
        .totalElements(info.getTotalElements())
        .totalPages(info.getTotalPages())
        .requestsInformation(requestsInformation)
        .build();
    return requestsInfo;
  }

}
