package com.cml.challenge.infraestructure.adapter.inbound.api.filter;

import com.cml.challenge.infraestructure.adapter.inbound.api.model.InterceptorInformation;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@AllArgsConstructor
public class RequestTransactionFilter implements Filter {

  private final RabbitTemplate rabbitTemplate;

  @Override
  public void doFilter(final ServletRequest req, final ServletResponse response,
                       final FilterChain chain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;

    String user = request.getUserPrincipal() != null ? request.getUserPrincipal().getName(): "ND";
    InterceptorInformation intReq = InterceptorInformation.builder()
        .username(user)
        .date(LocalDateTime.now())
        .ip(request.getRemoteAddr())
        .urlRequest(request.getRequestURL()
            + (request.getQueryString() != null?
            "?" + request.getQueryString(): ""))
        .build();

    rabbitTemplate.convertAndSend("request-interceptor",
        intReq);

    chain.doFilter(req, response);
  }
}
