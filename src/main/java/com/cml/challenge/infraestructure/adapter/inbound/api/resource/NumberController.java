package com.cml.challenge.infraestructure.adapter.inbound.api.resource;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.cml.challenge.application.port.primary.SumNumbers;
import com.cml.challenge.infraestructure.adapter.inbound.api.model.SumNumbersResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("number")
@Slf4j
@RequiredArgsConstructor
@Validated
public class NumberController {

    private final SumNumbers sumNumbers;

    @GetMapping(value = "/sum",
        produces = APPLICATION_JSON_VALUE)
    public SumNumbersResponse sumNumbers(
        @RequestParam("firstNumber") @Valid @NotNull Double firstNumber,
        @RequestParam("secondNumber") @Valid @NotNull Double secondNumber) {
        log.info("Sum info firstNumber: {}, secondNumber: {}", firstNumber, secondNumber);

        SumNumbersResponse result = SumNumbersResponse.builder()
            .result(sumNumbers.sum(firstNumber, secondNumber))
            .build();
        return result;
    }

}
