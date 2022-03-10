package com.cml.challenge.infraestructure.adapter.inbound.api.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cml.challenge.application.port.primary.SumNumbers;
import com.cml.challenge.infraestructure.adapter.config.UserTokenServiceConfigBean;
import com.cml.challenge.infraestructure.security.config.CustomLogoutHandler;
import com.cml.challenge.infraestructure.security.config.SecurityConfig;
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
@WebMvcTest(NumberController.class)
@Import({SecurityConfig.class, TestUserTokenServiceConfigBean.class, CustomLogoutHandler.class})
@AutoConfigureMockMvc(addFilters = false)
class NumberControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SumNumbers sumNumbers;

    @Test
    void givenSumNumberParameter_whenSumNumberAvailable_thenReturnJsonArray() throws Exception {
        when(sumNumbers.sum(anyDouble(), anyDouble())).thenReturn(7.0);
        mvc.perform(get("/number/sum")
                .contentType(MediaType.APPLICATION_JSON)
                    .param("firstNumber", "4")
                    .param("secondNumber", "3")
                    )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(7.0)));
    }

    @Test
    void givenInvalidNumberParameter_whenGetMeetingRoomAvailable_thenReturnError() throws Exception {

        mvc.perform(get("/number/sum")
            .contentType(MediaType.APPLICATION_JSON)
            .param("secondNumber", "3")
        )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

}