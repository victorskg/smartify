package com.github.victorskg.domain.customer;

import static java.util.Objects.nonNull;
import static lombok.AccessLevel.PRIVATE;

import java.util.regex.Pattern;

import com.github.victorskg.common.validator.BaseValidator;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class CustomerValidator {

    public static Customer validate(final Customer customer) {
        validateName(customer.getName());
        validatePhone(customer.getPhone());

        return customer;
    }

    private static void validateName(final String name) {
        final var fieldName = "Nome";
        BaseValidator.nonNull(name, fieldName);
        BaseValidator.notEmptyText(name, fieldName);
    }

    private static void validatePhone(String phone) {
        if (nonNull(phone)) {
            BaseValidator.textPattern(phone, Pattern.compile("[0-9]{8,9}"), "Telefone");
        }
    }

}