package com.github.victorskg.domain.product.category;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.github.victorskg.domain.BaseDomain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ProductCategory extends BaseDomain<ProductCategory> {

    @NotBlank(message = "{productCategory.name.notBlank}")
    @Size(message = "{productCategory.name.size}", min = 3, max = 255)
    private String name;

    @Builder
    private ProductCategory(final UUID uuid, final String name) {
        super(uuid);
        this.name = name;
        validateSelf();
    }

}
