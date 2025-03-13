package com.example.test.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Template {
  private String id;
  private String templateKey;
  private String html;
}
