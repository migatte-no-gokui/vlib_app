package com.vpacheco.vlib.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
  @Getter
  private String resourceName;
  @Getter
  private String fieldName;
  @Getter
  private Object fieldValue;

  public ResourceNotFoundException(String resourceName, String fieldName,
                                   Object fieldValue) {
    this.resourceName = resourceName;
    this.fieldName = fieldName;
    this.fieldValue = fieldValue;
  }
}
