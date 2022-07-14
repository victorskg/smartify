package com.github.victorskg.domain.product.category;

import static lombok.AccessLevel.PRIVATE;

import com.github.victorskg.common.validator.BaseValidator;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class ProductCategoryValidator {

    public static ProductCategory validate(final ProductCategory productCategory) {
        validateName(productCategory.getName());

        return productCategory;
    }

    private static void validateName(final String name) {
        final var fieldName = "Nome";
        BaseValidator.nonNull(name, fieldName);
        BaseValidator.notEmptyText(name, fieldName);
    }

}