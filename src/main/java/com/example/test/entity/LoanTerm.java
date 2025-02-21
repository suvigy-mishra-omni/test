package com.example.test.entity;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanTerm {
  private String id;
  private String tenure;
  private Integer interestRate;
}
