package com.github.victorskg.domain.product;

import static java.util.Objects.nonNull;
import static lombok.AccessLevel.PRIVATE;

import com.github.victorskg.common.validator.BaseValidator;
import com.github.victorskg.domain.product.category.ProductCategory;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class ProductValidator {

    public static Product validate(final Product product) {
        validateCategory(product.getCategory());
        validateDescription(product.getDescription());

        return product;
    }

    private static void validateCategory(final ProductCategory category) {
        final var fieldName = "Categoria";
        BaseValidator.nonNull(category, fieldName);
    }

    private static void validateDescription(final String description) {
        final var fieldName = "Descrição";
        if (nonNull(description)) {
            BaseValidator.notEmptyText(description, fieldName);
        }
    }

}