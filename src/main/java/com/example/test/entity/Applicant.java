package com.example.test.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Applicant {
  private String id;
  private String name;
  private String age;
  private String city;
}
