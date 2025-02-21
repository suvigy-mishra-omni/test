package com.example.test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Address {
  private String id;
  private String city;
  private String state;
  private Integer pinCode;
}
