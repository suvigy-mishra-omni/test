package com.example.test.util;

import com.example.test.dto.enums.ErrorStatus;
import com.example.test.dto.response.BaseResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ExternalApiConnect {
  private static final RestTemplate restTemplate = new RestTemplate();

  public static <Response> BaseResponse<Response> restExternalConnect(
      HttpMethod method, String url, Object requestBody, Map<String, Object> httpHeaders) {
    try {
      ResponseEntity<Response> response =
          restTemplate.exchange(
              url,
              method,
              new HttpEntity<>(requestBody, getHeaders(httpHeaders)),
              new ParameterizedTypeReference<>() {});

      return convertToBaseResponse(response);
    } catch (Exception exception) {
      return handleException(url, exception);
    }
  }

  public static <Response> BaseResponse<Response> restExternalConnect(
      HttpMethod method,
      String url,
      Object requestBody,
      Map<String, Object> httpHeaders,
      Map<String, Object> params) {
    try {
      ResponseEntity<Response> response =
          restTemplate.exchange(
              url,
              method,
              new HttpEntity<>(requestBody, getHeaders(httpHeaders)),
              new ParameterizedTypeReference<>() {},
              params);

      return convertToBaseResponse(response);
    } catch (Exception exception) {
      return handleException(url, exception);
    }
  }

  private static <Response> BaseResponse<Response> handleException(
      String url, Exception exception) {
    String message = exception.getMessage();

    ErrorStatus es = ErrorStatus.FAILED;
    int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

    log.error(
        ("{ExternalConnectUtils} Error while calling API {url} | Message {message} | Error {stack-trace}")
            .replace("{url}", url)
            .replace("{message}", message)
            .replace("{stack-trace}", Arrays.toString(exception.getStackTrace())));

    return BaseResponse.<Response>builder().es(es).message(message).statusCode(statusCode).build();
  }

  public static <T> BaseResponse<T> convertToBaseResponse(ResponseEntity<T> responseEntity) {
    return new BaseResponse<>(
        ErrorStatus.SUCCESSFUL,
        "Message",
        responseEntity.getStatusCodeValue(),
        responseEntity.getBody(),
        responseEntity.getHeaders().getDate());
  }

  private static HttpHeaders getHeaders(Map<String, Object> headerMap) {
    HttpHeaders httpHeaders = new HttpHeaders();

    if (Objects.nonNull(headerMap)) {
      for (var entrySet : headerMap.entrySet()) {
        httpHeaders.add(entrySet.getKey(), entrySet.getValue().toString());
      }
    }

    return httpHeaders;
  }
}
