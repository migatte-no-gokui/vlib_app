package com.vpacheco.vlib.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BookRequest {
  @NotBlank
  String criteria;
  @NotBlank
  int page;
  @NotBlank
  String direction;

  public BookRequest(String criteria, int page, String direction) {
    this.criteria = criteria;
    this.page = page;
    this.direction = direction;
  }
}
