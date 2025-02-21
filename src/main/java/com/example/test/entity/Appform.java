package com.example.test.entity;

import java.util.List;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appform {
  private String id;
  private String loanApplicationId;
  private String poolId;
  private String masterPoolId;
  private LoanTerm loanTerm;
  private List<Borrower> borrowers;
}
