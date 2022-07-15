package com.github.victorskg.domain.supplier;

import java.util.Optional;
import java.util.UUID;

public interface SupplierGateway {

    Supplier create(final Supplier supplier);

    Supplier update(final Supplier supplier);

    Optional<Supplier> findByUuid(final UUID uuid);

    void delete(final Supplier supplier);

}