package com.example.test.dto.response;

import com.example.test.dto.enums.ErrorStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse<T> {
  @JsonProperty("es")
  private ErrorStatus es;

  @JsonProperty("message")
  private String message;

  @JsonProperty("statusCode")
  private int statusCode;

  @JsonProperty("data")
  private T data;

  @Builder.Default private Long time = new Date().getTime();

  public boolean isSuccessful() {
    return this.es.equals(ErrorStatus.SUCCESSFUL);
  }

  public boolean isRejected() {
    return this.es.equals(ErrorStatus.REJECTED);
  }

  public boolean isFailed() {
    return this.es.equals(ErrorStatus.FAILED);
  }

  public boolean isInterrupted() {
    return this.es.equals(ErrorStatus.INTERRUPTED);
  }
}
