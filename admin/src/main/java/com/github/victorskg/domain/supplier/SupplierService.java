package com.github.victorskg.domain.supplier;

import static com.github.victorskg.common.exception.BusinessExceptionMessage.ENTITY_NOT_FOUND_BY_ID;

import java.util.UUID;

import com.github.victorskg.common.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SupplierService {

    private final SupplierGateway gateway;

    public Supplier create(final Supplier supplier) {
        return gateway.create(supplier);
    }

    public Supplier update(final Supplier supplier) {
        get(supplier.getUuid());
        return gateway.update(supplier);
    }

    public void delete(final UUID uuid) {
        final var supplier = get(uuid);
        gateway.delete(supplier);
    }

    public Supplier get(final UUID uuid) {
        return gateway.findByUuid(uuid)
                .orElseThrow(() -> new BusinessException(ENTITY_NOT_FOUND_BY_ID.makeMessage("Fornecedor", uuid)));
    }

}
