package com.example.test.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorStatus {
  SUCCESSFUL(0),
  FAILED(1),
  REJECTED(2),
  UNKNOWN(3),
  TIMED_OUT(4),
  INTERNAL_SERVER_ERROR(5),
  INTERRUPTED(6);

  public final int value;

  public static ErrorStatus getStatusByValue(int value) {
    for (ErrorStatus status : ErrorStatus.values()) {
      if (status.value == value) {
        return status;
      }
    }
    return null;
  }
}
