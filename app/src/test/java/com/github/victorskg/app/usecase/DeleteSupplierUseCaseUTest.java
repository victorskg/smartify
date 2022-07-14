package com.github.victorskg.app.usecase;

import static com.github.victorskg.core.domain.exception.BusinessExceptionMessage.ENTITY_NOT_FOUND_BY_ID;
import static org.mockito.Mockito.times;

import java.util.Optional;
import java.util.UUID;

import com.github.victorskg.app.repository.SupplierRepository;
import com.github.victorskg.core.domain.Supplier;
import com.github.victorskg.core.domain.exception.BusinessException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DeleteSupplierUseCaseUTest {

    private final SupplierRepository repository = Mockito.mock(SupplierRepository.class);
    private final DeleteSupplierUseCase useCase = new DeleteSupplierUseCase(repository);

    @Test
    @DisplayName("Should delete Supplier")
    void shouldDeleteSupplier() {
        final var supplier = Supplier.of(UUID.randomUUID(), "Supplier");
        Mockito.when(repository.findByUuid(supplier.getUuid())).thenReturn(Optional.of(supplier));
        Mockito.doNothing().when(repository).delete(supplier);
        useCase.execute(supplier.getUuid());
        Mockito.verify(repository, times(1)).delete(supplier);
    }

    @Test
    @DisplayName("Should not delete Supplier when it doesn't exist")
    void shouldNotDeleteSupplierWhenItDoesNotExist() {
        final var supplier = Supplier.of(UUID.randomUUID(), "Supplier");
        Mockito.when(repository.findByUuid(supplier.getUuid())).thenReturn(Optional.empty());
        final var exception = Assertions.assertThrowsExactly(BusinessException.class,
                () -> useCase.execute(supplier.getUuid()));
        Assertions.assertEquals(ENTITY_NOT_FOUND_BY_ID.makeMessage("Fornecedor", supplier.getUuid()), exception.getMessage());
    }
    
}