package com.github.victorskg.common.validator;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.github.victorskg.common.exception.FieldValidatorException;

public abstract class SelfValidating<T> {

    private final Validator validator;

    protected SelfValidating() {
      final var factory = Validation.buildDefaultValidatorFactory();
      validator = factory.getValidator();
    }
  
    @SuppressWarnings("unchecked")
    protected void validateSelf() {
      final var violations = validator.validate((T) this);
      if (!violations.isEmpty()) {
        throw new FieldValidatorException(buildMessage(violations));
      }
    }

    private String buildMessage(Set<ConstraintViolation<T>> constraints) {
        return constraints.stream().map(ConstraintViolation<T>::getMessage).collect(Collectors.joining(", "));
    }
    
}
