package com.github.victorskg.domain.product.category;

import static com.github.victorskg.common.exception.BusinessExceptionMessage.ENTITY_NOT_FOUND_BY_ID;

import java.util.UUID;

import com.github.victorskg.common.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductCategoryService {

    private final ProductCategoryGateway gateway;

    public ProductCategory create(final ProductCategory productCategory) {
        return gateway.create(productCategory);
    }

    public ProductCategory update(final ProductCategory productCategory) {
        get(productCategory.getUuid());
        return gateway.update(productCategory);
    }

    public ProductCategory get(final UUID uuid) {
        return gateway.findByUuid(uuid)
                .orElseThrow(() -> new BusinessException(ENTITY_NOT_FOUND_BY_ID.makeMessage("Categoria de Produto", uuid)));
    }
    
}
