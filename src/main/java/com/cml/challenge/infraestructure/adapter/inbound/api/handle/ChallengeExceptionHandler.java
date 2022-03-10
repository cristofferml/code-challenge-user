package com.cml.challenge.infraestructure.adapter.inbound.api.handle;

import com.cml.challenge.application.exception.UserNameExistException;
import io.jsonwebtoken.ExpiredJwtException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ChallengeExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler(value = {UserNameExistException.class})
  public ResponseEntity<Object> handleUserNameExist(UserNameExistException ule) {
    HttpStatus httpStatus = HttpStatus.CONFLICT;

    ApplicationExceptionErrorResponse response = ApplicationExceptionErrorResponse.builder()
        .code(httpStatus.value())
        .timestamp(LocalDateTime.now())
        .message(httpStatus.getReasonPhrase())
        .detail(Collections.singletonMap("error", ule.getMessage()))
        .build();

    return new ResponseEntity<>(response, httpStatus);
  }

  @ExceptionHandler(value = {ExpiredJwtException.class})
  public ResponseEntity<Object> handleExpiredJwt(ExpiredJwtException ule) {
    HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

    ApplicationExceptionErrorResponse response = ApplicationExceptionErrorResponse.builder()
        .code(httpStatus.value())
        .timestamp(LocalDateTime.now())
        .message(httpStatus.getReasonPhrase())
        .detail(Collections.singletonMap("error", ule.getMessage()))
        .build();

    return new ResponseEntity<>(response, httpStatus);
  }

  @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
  public ResponseEntity<Object> handleExpiredJwt(MethodArgumentTypeMismatchException ule) {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    ApplicationExceptionErrorResponse response = ApplicationExceptionErrorResponse.builder()
        .code(httpStatus.value())
        .timestamp(LocalDateTime.now())
        .message(httpStatus.getReasonPhrase())
        .detail(Collections.singletonMap("error", ule.getMessage()))
        .build();

    return new ResponseEntity<>(response, httpStatus);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status,
      WebRequest request) {

    ApplicationExceptionErrorResponse response = ApplicationExceptionErrorResponse.builder()
        .code(status.value())
        .timestamp(LocalDateTime.now())
        .message(status.getReasonPhrase())
        .detail(Collections.singletonMap("error", ex.getMessage()))
        .build();
    return handleExceptionInternal(ex, response, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers,
                                                                HttpStatus status,
                                                                WebRequest request) {
    ApplicationExceptionErrorResponse detail =
        getInputDataValidationErrors(ex.getBindingResult(), status);

    return handleExceptionInternal(ex, detail, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleBindException(
      BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    ApplicationExceptionErrorResponse detail =
        getInputDataValidationErrors(ex.getBindingResult(), status);
    return handleExceptionInternal(ex, detail, headers, status, request);
  }

  private ApplicationExceptionErrorResponse getInputDataValidationErrors(
      BindingResult bindingResult, HttpStatus status) {

    Map<String, String> errorMessage = bindingResult.getFieldErrors().stream()
        .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage,
            (oldValue, newValue) -> oldValue + "|" + newValue));

    ApplicationExceptionErrorResponse detail = ApplicationExceptionErrorResponse.builder()
        .timestamp(LocalDateTime.now())
        .message(status.getReasonPhrase())
        .detail(errorMessage)
        .code(status.value())
        .build();
    return detail;
  }
}
