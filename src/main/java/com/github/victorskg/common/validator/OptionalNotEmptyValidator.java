package com.github.victorskg.common.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.github.victorskg.common.validator.annotation.OptionalNotEmpty;

public class OptionalNotEmptyValidator implements ConstraintValidator<OptionalNotEmpty, String> {

    private OptionalNotEmpty optionalNotEmpty;

    @Override
    public void initialize(final OptionalNotEmpty optionalNotEmpty) {
        this.optionalNotEmpty = optionalNotEmpty;
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return Optional.ofNullable(value)
            .map(String::trim)
            .map(this::verifyLength)
            .orElse(true);
    }

    private boolean verifyLength(final String value) {
        final int minLength = optionalNotEmpty.minLength();
        final int maxLength = optionalNotEmpty.maxLength();
        return value.length() >= minLength && value.length() <= maxLength;
    }
    
}
