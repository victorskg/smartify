package com.github.victorskg.app.usecase;

import static com.github.victorskg.core.domain.exception.BusinessExceptionMessage.ENTITY_NOT_FOUND_BY_ID;

import java.util.Optional;
import java.util.UUID;

import com.github.victorskg.app.repository.CustomerRepository;
import com.github.victorskg.core.domain.Customer;
import com.github.victorskg.core.domain.exception.BusinessException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UpdateCustomerUseCaseUTest {

    private final CustomerRepository repository = Mockito.mock(CustomerRepository.class);
    private final UpdateCustomerUseCase useCase = new UpdateCustomerUseCase(repository);

    @Test
    @DisplayName("Should update Customer")
    void shouldUpdateCustomer() {
        final var customer = Customer.of(UUID.randomUUID(), "Customer", "999999999");
        Mockito.when(repository.findByUuid(customer.getUuid())).thenReturn(Optional.of(customer));
        Mockito.when(repository.update(customer)).thenReturn(customer);
        final var updatedCustomer = useCase.execute(customer);
        Assertions.assertEquals(updatedCustomer, customer);
    }

    @Test
    @DisplayName("Should not update Customer when it doesn't exist")
    void shouldNotUpdateCustomerWhenItDoesNotExist() {
        final var customer = Customer.of(UUID.randomUUID(), "Customer", "999999999");
        Mockito.when(repository.findByUuid(customer.getUuid())).thenReturn(Optional.empty());
        final var exception = Assertions.assertThrows(BusinessException.class,
                () -> useCase.execute(customer));
        Assertions.assertEquals(ENTITY_NOT_FOUND_BY_ID.makeMessage("Cliente", customer.getUuid()), exception.getMessage());
    }

}
