package com.github.victorskg.domain.product;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.github.victorskg.domain.BaseDomain;
import com.github.victorskg.domain.product.category.ProductCategory;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseDomain<Product> {

    @NotBlank(message = "{product.name.notEmpty}")
    private String name;
    
    private String description;

    @NotNull(message = "{product.category.notNull}")
    private ProductCategory category;

    private Product(final String name, final String description, final ProductCategory category) {
        super(null);
        this.name = name;
        this.description = description;
        this.category = category;
        validateSelf();
    }

    private Product(final UUID uuid, final String name, final String description, final ProductCategory category) {
        super(uuid);
        this.name = name;
        this.description = description;
        this.category = category;
        validateSelf();
    }

    public static Product of(final String name, final String description, final ProductCategory category) {
        return ProductValidator.validate(new Product(name, description, category));
    }

    public static Product of(final UUID uuid, final String name, final String description, final ProductCategory category) {
        return ProductValidator.validate(new Product(uuid, name, description, category));
    }

}