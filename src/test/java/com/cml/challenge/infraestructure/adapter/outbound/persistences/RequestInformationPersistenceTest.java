package com.cml.challenge.infraestructure.adapter.outbound.persistences;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cml.challenge.domain.model.Pagination;
import com.cml.challenge.domain.model.RequestInformation;
import com.cml.challenge.domain.model.RequestsInformation;
import com.cml.challenge.infraestructure.adapter.outbound.persistences.entities.RequestInformationEntity;
import com.cml.challenge.infraestructure.adapter.outbound.persistences.respository.RequestInformationRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class RequestInformationPersistenceTest {

  @Mock
  private RequestInformationRepository requestInformationRepository;

  @InjectMocks
  private RequestInformationPersistence requestInformationPersistence;


  @Test
  void insertNewRequestInformationOk () {
    RequestInformation rInformation = RequestInformation.builder()
        .urlRequest("http://localhost:9090/test")
        .ip("0.0.0.0")
        .date(LocalDateTime.now())
        .username("usertest")
        .build();

    RequestInformationEntity entity = RequestInformationEntity.builder()
        .username("usertest").build();

    when(requestInformationRepository.save(any(RequestInformationEntity.class))).thenReturn(entity);

    ArgumentCaptor<RequestInformationEntity> argument =
        ArgumentCaptor.forClass(RequestInformationEntity.class);

    requestInformationPersistence.saveRequestInfo(rInformation);
    verify(requestInformationRepository).save(argument.capture());
    assertEquals(rInformation.getUsername(),
        argument.getValue().getUsername());

  }

  @Test
  void getRequestInformationPageableOk() {

    Pagination pagination = Pagination.builder()
        .page(3)
        .pageSize(10)
        .build();

    Page<RequestInformationEntity> info = (Page<RequestInformationEntity>) mock(Page.class);
    RequestInformationEntity rInfo = RequestInformationEntity.builder()
        .ip("0:0:0:0:0:0:0:1")
        .username("Test")
        .urlRequest("http://localhost:8080/request-info/page?page=1&size=99")
        .date(LocalDateTime.now())
        .build();

    RequestInformationEntity rInfo2 = RequestInformationEntity.builder()
        .ip("0:0:0:0:0:0:0:1")
        .username("Test2")
        .urlRequest("http://localhost:8080/request-info/page?page=1&size=99")
        .date(LocalDateTime.now())
        .build();

    List<RequestInformationEntity> listRequest = Arrays.asList(rInfo, rInfo2);
    when(info.getContent()).thenReturn(listRequest);

    when(requestInformationRepository.findAll(any(Pageable.class))).thenReturn(info);

    RequestsInformation finalRequestsInfo =
        requestInformationPersistence.findAllRequestInformationPageable(pagination);

    assertNotNull(finalRequestsInfo);
    assertEquals(2, finalRequestsInfo.getRequestsInformation().size());
    assertEquals(pagination.getPage(), finalRequestsInfo.getCurrentPage());
    assertEquals(rInfo.getUsername(),
        finalRequestsInfo.getRequestsInformation().get(0).getUsername());


  }

}