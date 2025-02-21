package com.example.test.entity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Borrower {
  private String id;
  private String name;
  private Integer age;
  private Address address;
}
