package com.github.victorskg.domain.product;

import static com.github.victorskg.common.exception.BusinessExceptionMessage.ENTITY_NOT_FOUND_BY_ID;

import java.util.UUID;

import com.github.victorskg.common.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductService {
    
    private final ProductGateway gateway;

    public Product create(final Product product) {
        return gateway.create(product);
    }

    public Product execute(final Product product) {
        get(product.getUuid());
        return gateway.update(product);
    }

    private Product get(final UUID uuid) {
        return gateway.findByUuid(uuid)
                .orElseThrow(() -> new BusinessException(ENTITY_NOT_FOUND_BY_ID.makeMessage("Produto", uuid)));
    }

}
