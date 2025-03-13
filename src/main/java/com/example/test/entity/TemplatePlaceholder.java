package com.example.test.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TemplatePlaceholder {
  private String id;
  private String templateId;
  private String placeholder;
  private String dataSource;
  private String defaultValue;
  private String transformer;
}
