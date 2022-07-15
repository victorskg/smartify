package com.github.victorskg.domain.product;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.github.victorskg.common.validator.annotation.OptionalNotEmpty;
import com.github.victorskg.domain.BaseDomain;
import com.github.victorskg.domain.product.category.ProductCategory;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseDomain<Product> {

    @NotBlank(message = "{product.name.notEmpty}")
    private String name;

    @OptionalNotEmpty(message = "{product.description.optionalNotEmpty}", minLength = 3, maxLength = 255)
    private String description;

    @NotNull(message = "{product.category.notNull}")
    private ProductCategory category;

    private Product() {
        super(null);
    }

    @Builder
    private Product(final UUID uuid, final String name, final String description, final ProductCategory category) {
        super(uuid);
        this.name = name;
        this.description = description;
        this.category = category;
        validateSelf();
    }

    private static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public static ProductBuilder builder(final String name, final ProductCategory productCategory) {
        return builder().name(name).category(productCategory);
    }

}
