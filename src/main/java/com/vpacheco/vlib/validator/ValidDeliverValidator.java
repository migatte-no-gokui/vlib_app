package com.vpacheco.vlib.validator;

import com.vpacheco.vlib.model.Requisition;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class ValidDeliverValidator
  implements ConstraintValidator<ValidDeliverDate, Requisition> {

  @Override
  public void initialize(ValidDeliverDate constraintAnnotation) {

  }

  @Override
  public boolean isValid(Requisition requisition, ConstraintValidatorContext context) {
    LocalDateTime datePicked = requisition.getPickupDate();
    LocalDateTime dateDelivered = requisition.getDeliveryDate();
    if (requisition == null || (datePicked == null && dateDelivered == null))
      return true;
    else if (datePicked == null && dateDelivered != null)
      return false;

    return dateDelivered.compareTo(datePicked) >= 0;
  }
}
