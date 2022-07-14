package com.github.victorskg.app.usecase;

import static com.github.victorskg.core.domain.exception.BusinessExceptionMessage.ENTITY_NOT_FOUND_BY_ID;

import java.util.Optional;
import java.util.UUID;

import com.github.victorskg.app.repository.SupplierRepository;
import com.github.victorskg.core.domain.Supplier;
import com.github.victorskg.core.domain.exception.BusinessException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UpdateSupplierUseCaseUTest {

    private final SupplierRepository repository = Mockito.mock(SupplierRepository.class);
    private final UpdateSupplierUseCase useCase = new UpdateSupplierUseCase(repository);

    @Test
    @DisplayName("Should update Supplier")
    void shouldUpdateSupplier() {
        final var supplier = Supplier.of(UUID.randomUUID(), "Supplier");
        Mockito.when(repository.findByUuid(supplier.getUuid())).thenReturn(Optional.of(supplier));
        Mockito.when(repository.update(supplier)).thenReturn(supplier);
        final var updatedSupplier = useCase.execute(supplier);
        Assertions.assertEquals(updatedSupplier, supplier);
    }

    @Test
    @DisplayName("Should not update Supplier when it doesn't exist")
    void shouldNotUpdateSupplierWhenItDoesNotExist() {
        final var supplier = Supplier.of(UUID.randomUUID(), "Supplier");
        Mockito.when(repository.findByUuid(supplier.getUuid())).thenReturn(Optional.empty());
        final var exception = Assertions.assertThrows(BusinessException.class,
                () -> useCase.execute(supplier));
        Assertions.assertEquals(ENTITY_NOT_FOUND_BY_ID.makeMessage("Fornecedor", supplier.getUuid()), exception.getMessage());
    }
    
}