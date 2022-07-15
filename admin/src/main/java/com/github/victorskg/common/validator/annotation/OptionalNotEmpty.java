package com.github.victorskg.common.validator.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.github.victorskg.common.validator.OptionalNotEmptyValidator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({PARAMETER, FIELD})
@Constraint(validatedBy = OptionalNotEmptyValidator.class)
public @interface OptionalNotEmpty {
    
    String message() default "The given value must not be empty";

    int minLength() default 0;

    int maxLength() default Integer.MAX_VALUE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}
