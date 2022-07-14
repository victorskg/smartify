package com.github.victorskg.app.usecase;

import com.github.victorskg.app.repository.SupplierRepository;
import com.github.victorskg.core.domain.Supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CreateSupplierUseCaseUTest {

    private final SupplierRepository repository = Mockito.mock(SupplierRepository.class);
    private final CreateSupplierUseCase useCase = new CreateSupplierUseCase(repository);

    @Test
    @DisplayName("Should create Supplier")
    void shouldCreateSupplier() {
        final var supplier = Supplier.of("Supplier");
        Mockito.when(repository.create(supplier)).thenReturn(supplier);
        final var createdSupplier = useCase.execute(supplier);
        Assertions.assertEquals(supplier, createdSupplier);
    }
    
}