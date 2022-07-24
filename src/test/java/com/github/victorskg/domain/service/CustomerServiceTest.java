package com.github.victorskg.domain.service;

import static com.github.victorskg.common.exception.BusinessExceptionMessage.ENTITY_NOT_FOUND_BY_ID;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.github.victorskg.common.exception.BusinessException;
import com.github.victorskg.domain.customer.Customer;
import com.github.victorskg.domain.customer.CustomerGateway;
import com.github.victorskg.domain.customer.CustomerService;

class CustomerServiceTest {

    private final CustomerGateway gateway;
    private final CustomerService service;

    CustomerServiceTest() {
        gateway = Mockito.mock(CustomerGateway.class);
        service = new CustomerService(gateway);
    }

    @Test
    @DisplayName("Should create Customer")
    void shouldCreateCustomer() {
        Mockito.when(gateway.create(any(Customer.class))).then(returnsFirstArg());
        final var customer = Customer.of("Customer", "99999999");
        final var savedCustomer = service.create(customer);
        Assertions.assertEquals(customer, savedCustomer);
    }

    @Test
    @DisplayName("Should update Customer")
    void shouldUpdateCustomer() {
        Mockito.when(gateway.update(any(Customer.class))).then(returnsFirstArg());
        final var customer = Customer.of(UUID.randomUUID(), "Customer Updated", "99999999");
        Mockito.when(gateway.findByUuid(any(UUID.class))).thenReturn(Optional.of(customer));
        final var savedCustomer = service.update(customer);
        Assertions.assertEquals(customer, savedCustomer);
    }

    @Test
    @DisplayName("Should not update non existent Customer")
    void shouldNotUpdateNonExistentCustomer() {
        Mockito.when(gateway.findByUuid(any(UUID.class))).thenReturn(Optional.empty());
        final var customer = Customer.of(UUID.randomUUID(), "Customer Updated", "99999999");
        final var exception = Assertions.assertThrowsExactly(BusinessException.class, () -> service.update(customer));
        Assertions.assertEquals(ENTITY_NOT_FOUND_BY_ID.makeMessage("Cliente", customer.getUuid()),
                exception.getMessage());
    }
    
    @Test
    @DisplayName("Should get Customer")
    void shouldGetCustomer() {
        final var customer = Customer.of(UUID.randomUUID(), "Customer", "99999999");
        Mockito.when(gateway.findByUuid(any(UUID.class))).thenReturn(Optional.of(customer));
        final var customerFromService = service.get(customer.getUuid());
        Assertions.assertEquals(customer, customerFromService);
    }

    @Test
    @DisplayName("Should not get non existent Customer")
    void shouldNotGetNonExistentCustomer() {
        Mockito.when(gateway.findByUuid(any(UUID.class))).thenReturn(Optional.empty());
        final var customerUUID = UUID.randomUUID();
        final var exception = Assertions.assertThrowsExactly(BusinessException.class, () -> service.get(customerUUID));
        Assertions.assertEquals(ENTITY_NOT_FOUND_BY_ID.makeMessage("Cliente",customerUUID),
                exception.getMessage());
    }
    
}