package com.vpacheco.vlib.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BookRequest {
  @NotBlank
  private String criteria;
  @NotNull
  private int page;
  @NotBlank
  private String direction;
}
