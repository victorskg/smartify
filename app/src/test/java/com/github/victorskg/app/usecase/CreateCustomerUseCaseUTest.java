package com.github.victorskg.app.usecase;

import com.github.victorskg.app.repository.CustomerRepository;
import com.github.victorskg.core.domain.Customer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CreateCustomerUseCaseUTest {

    private final CustomerRepository repository = Mockito.mock(CustomerRepository.class);
    private final CreateCustomerUseCase useCase = new CreateCustomerUseCase(repository);

    @Test
    @DisplayName("Should create Customer")
    void shouldCreateCustomer() {
        final var customer = Customer.of("Customer", "999999999");
        Mockito.when(repository.create(customer)).thenReturn(customer);
        final var createdCustomer = useCase.execute(customer);
        Assertions.assertEquals(createdCustomer, customer);
    }

}
