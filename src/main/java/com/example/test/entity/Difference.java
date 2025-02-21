package com.example.test.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Difference {
  private String key;
  private String fullPath;
  private Object originalValue;
  private Object updatedValue;
  private String changeType;

  @Override
  public String toString() {
    return "CHANGE TYPE => {change-type} | KEY => {key} | FULL PATH => {full-path} | ORIGINAL VALUE => {original-value} | UPDATED VALUE => {updated-value}"
        .replace("{key}", key)
        .replace("{full-path}", fullPath)
        .replace("{change-type}", String.valueOf(changeType))
        .replace("{original-value}", String.valueOf(originalValue))
        .replace("{updated-value}", String.valueOf(updatedValue));
  }
}
