package com.github.victorskg.domain.supplier;

import static lombok.AccessLevel.PRIVATE;

import com.github.victorskg.common.validator.BaseValidator;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class SupplierValidator {
    
    public static Supplier validate(final Supplier supplier) {
        validateName(supplier.getName());

        return supplier;
    }

    private static void validateName(final String name) {
        final var fieldName = "Nome";
        BaseValidator.nonNull(name, fieldName);
        BaseValidator.notEmptyText(name, fieldName);
    }

}