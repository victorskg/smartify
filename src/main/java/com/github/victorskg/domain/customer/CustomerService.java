package com.github.victorskg.domain.customer;

import static com.github.victorskg.common.exception.BusinessExceptionMessage.ENTITY_NOT_FOUND_BY_ID;

import java.util.UUID;

import com.github.victorskg.common.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerService {

    private final CustomerGateway gateway;

    public Customer create(final Customer customer) {
        return gateway.create(customer);
    }

    public Customer update(final Customer customer) {
        get(customer.getUuid());
        return gateway.update(customer);
    }

    public Customer get(final UUID uuid) {
        return gateway.findByUuid(uuid)
            .orElseThrow(() -> new BusinessException(ENTITY_NOT_FOUND_BY_ID.makeMessage("Cliente", uuid)));
    }

}
