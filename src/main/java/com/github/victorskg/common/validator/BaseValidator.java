package com.github.victorskg.common.validator;

import static com.github.victorskg.common.exception.FieldValidatorExceptionMessage.NON_NULL;
import static com.github.victorskg.common.exception.FieldValidatorExceptionMessage.NOT_EMPTY_TEXT;
import static com.github.victorskg.common.exception.FieldValidatorExceptionMessage.POSITIVE_AND_BIGGER_THAN_ZERO;
import static com.github.victorskg.common.exception.FieldValidatorExceptionMessage.TEXT_PATTERN;
import static com.github.victorskg.common.exception.FieldValidatorExceptionMessage.TEXT_SIZE;
import static java.util.Objects.isNull;
import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import com.github.victorskg.common.exception.FieldValidatorException;

import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = PRIVATE)
public class BaseValidator {

    public static void nonNull(final Object field, @NonNull final String fieldName) {
        if (isNull(field))
            throw new FieldValidatorException(NON_NULL.makeMessage(fieldName));
    }

    public static void textSize(@NonNull final String field, final int exactSize, @NonNull final String fieldName) {
        if (field.trim().length() != exactSize)
            throw new FieldValidatorException(TEXT_SIZE.makeMessage(fieldName, exactSize));
    }

    public static void minTextSize(@NonNull final String field, final int minTextSize, @NonNull final String fieldName) {
        if (field.trim().length() >= minTextSize)
            throw new FieldValidatorException(TEXT_SIZE.makeMessage(fieldName, minTextSize));
    }

    public static void maxTextSize(@NonNull final String field, final int maxTextSize, @NonNull final String fieldName) {
        if (field.trim().length() <= maxTextSize)
            throw new FieldValidatorException(TEXT_SIZE.makeMessage(fieldName, maxTextSize));
    }

    public static void notEmptyText(@NonNull final String field, @NonNull final String fieldName) {
        if (field.trim().isEmpty())
            throw new FieldValidatorException(NOT_EMPTY_TEXT.makeMessage(fieldName));
    }

    public static void textPattern(@NonNull final String field, @NonNull Pattern pattern, @NonNull final String fieldName) {
        if (!pattern.matcher(field).matches())
            throw new FieldValidatorException(TEXT_PATTERN.makeMessage(fieldName));
    }

    public static void positiveAndBiggerThanZero(@NonNull final BigDecimal field, @NonNull final String fieldName) {
        if (field.compareTo(BigDecimal.ZERO) <= 0)
            throw new FieldValidatorException(POSITIVE_AND_BIGGER_THAN_ZERO.makeMessage(fieldName));
    }

}