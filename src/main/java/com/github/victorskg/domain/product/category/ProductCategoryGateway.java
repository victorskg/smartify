package com.github.victorskg.domain.product.category;

import java.util.Optional;
import java.util.UUID;

public interface ProductCategoryGateway {

    ProductCategory create(final ProductCategory productCategory);

    ProductCategory update(final ProductCategory productCategory);

    Optional<ProductCategory> findByUuid(final UUID uuid);

}
