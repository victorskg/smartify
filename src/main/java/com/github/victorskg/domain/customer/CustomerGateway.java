package com.github.victorskg.domain.customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerGateway {

    Customer create(final Customer customer);

    Customer update(final Customer customer);

    Optional<Customer> findByUuid(final UUID uuid);

}
