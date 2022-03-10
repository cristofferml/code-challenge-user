package com.cml.challenge.infraestructure.adapter.inbound.api.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cml.challenge.application.port.primary.GetAllRequestsInformationPageable;
import com.cml.challenge.domain.model.Pagination;
import com.cml.challenge.domain.model.RequestInformation;
import com.cml.challenge.domain.model.RequestsInformation;
import com.cml.challenge.infraestructure.security.config.CustomLogoutHandler;
import com.cml.challenge.infraestructure.security.config.SecurityConfig;
import java.time.LocalDateTime;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@WebMvcTest(RequestInfoController.class)
@Import({SecurityConfig.class, TestUserTokenServiceConfigBean.class, CustomLogoutHandler.class})
@AutoConfigureMockMvc(addFilters = false)
class RequestInfoControllerTest {

  @MockBean
  private GetAllRequestsInformationPageable getAllRequestsInformationPageable;

  @Autowired
  private MockMvc mvc;

  @Test
  void givenPaginationParameter_whenGetAllRequestInformationAvailable_thenReturnJsonArray() throws Exception {

    RequestInformation rInfo = RequestInformation.builder()
        .ip("0:0:0:0:0:0:0:1")
        .username("Test")
        .urlRequest("http://localhost:8080/request-info/page?page=1&size=99")
        .date(LocalDateTime.now())
        .build();

    RequestInformation rInfo2 = RequestInformation.builder()
        .ip("0:0:0:0:0:0:0:1")
        .username("Test2")
        .urlRequest("http://localhost:8080/request-info/page?page=1&size=99")
        .date(LocalDateTime.now())
        .build();

    RequestsInformation result = RequestsInformation.builder()
        .requestsInformation(Arrays.asList(rInfo, rInfo2))
        .totalPages(10)
        .totalElements(100)
        .currentPage(2)
        .build();
    when(getAllRequestsInformationPageable.getAllRequestInformationPageable(any(Pagination.class)))
        .thenReturn(result);

    mvc.perform(get("/request-info/page")
        .contentType(MediaType.APPLICATION_JSON)
        .param("page", "4")
        .param("size", "3")
    )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.requestInformation", hasSize(2)))
        .andExpect(jsonPath("$.requestInformation[0].username", is(rInfo.getUsername())));
  }
}