package com.github.victorskg.domain.product;

import java.util.Optional;
import java.util.UUID;

public interface ProductGateway {

    Product create(final Product product);

    Product update(final Product product);

    Optional<Product> findByUuid(final UUID uuid);

}