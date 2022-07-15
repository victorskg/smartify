package com.github.victorskg.domain.product.category;

import java.util.UUID;

import com.github.victorskg.domain.BaseDomain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ProductCategory extends BaseDomain<ProductCategory> {

    private String name;

    private ProductCategory(final String name) {
        super(null);
        this.name = name;
        validateSelf();
    }

    private ProductCategory(final UUID uuid, final String name) {
        super(uuid);
        this.name = name;
        validateSelf();
    }

    public static ProductCategory of(final String name) {
        return ProductCategoryValidator.validate(new ProductCategory(name));
    }

    public static ProductCategory of(final UUID uuid, final String name) {
        return ProductCategoryValidator.validate(new ProductCategory(uuid, name));
    }

}
