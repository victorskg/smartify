package com.github.victorskg.domain.service;

import static com.github.victorskg.common.exception.BusinessExceptionMessage.ENTITY_NOT_FOUND_BY_ID;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.github.victorskg.common.exception.BusinessException;
import com.github.victorskg.domain.supplier.Supplier;
import com.github.victorskg.domain.supplier.SupplierGateway;
import com.github.victorskg.domain.supplier.SupplierService;

class SupplierServiceTest {

    private final SupplierGateway gateway;
    private final SupplierService service;

    SupplierServiceTest() {
        gateway = Mockito.mock(SupplierGateway.class);
        service = new SupplierService(gateway);
    }

    @Test
    @DisplayName("Should create Supplier")
    void shouldCreateSupplier() {
        Mockito.when(gateway.create(any(Supplier.class))).then(returnsFirstArg());
        final var supplier = Supplier.of("Supplier");
        final var savedSupplier = service.create(supplier);
        Assertions.assertEquals(supplier, savedSupplier);
    }

    @Test
    @DisplayName("Should update Supplier")
    void shouldUpdateSupplier() {
        Mockito.when(gateway.update(any(Supplier.class))).then(returnsFirstArg());
        final var supplier = Supplier.of(UUID.randomUUID(), "Supplier Updated");
        Mockito.when(gateway.findByUuid(any(UUID.class))).thenReturn(Optional.of(supplier));
        final var savedSupplier = service.update(supplier);
        Assertions.assertEquals(supplier, savedSupplier);
    }

    @Test
    @DisplayName("Should not update non existent Supplier")
    void shouldNotUpdateNonExistentSupplier() {
        Mockito.when(gateway.update(any(Supplier.class))).then(returnsFirstArg());
        Mockito.when(gateway.findByUuid(any(UUID.class))).thenReturn(Optional.empty());
        final var supplier = Supplier.of(UUID.randomUUID(), "Supplier Updated");
        final var exception = Assertions.assertThrowsExactly(BusinessException.class, () -> service.update(supplier));
        Assertions.assertEquals(ENTITY_NOT_FOUND_BY_ID.makeMessage("Fornecedor", supplier.getUuid()),
                exception.getMessage());
    }

    @Test
    @DisplayName("Should delete Supplier")
    void shouldDeleteSupplier() {
        Mockito.doNothing().when(gateway).delete(any(Supplier.class));
        final var supplier = Supplier.of(UUID.randomUUID(), "Supplier");
        Mockito.when(gateway.findByUuid(any(UUID.class))).thenReturn(Optional.of(supplier));
        service.delete(supplier.getUuid());
        Mockito.verify(gateway, times(1)).delete(any());
    }

    @Test
    @DisplayName("Should not delete non existent Supplier")
    void shouldNotDeleteNonExistentSupplier() {
        Mockito.when(gateway.update(any(Supplier.class))).then(returnsFirstArg());
        Mockito.when(gateway.findByUuid(any(UUID.class))).thenReturn(Optional.empty());
        final var supplier = Supplier.of(UUID.randomUUID(), "Supplier Updated");
        final var exception = Assertions.assertThrowsExactly(BusinessException.class, () -> service.update(supplier));
        Assertions.assertEquals(ENTITY_NOT_FOUND_BY_ID.makeMessage("Fornecedor", supplier.getUuid()),
                exception.getMessage());
    }
    
    @Test
    @DisplayName("Should get Supplier")
    void shouldGetSupplier() {
        final var supplier = Supplier.of(UUID.randomUUID(), "Supplier Updated");
        Mockito.when(gateway.findByUuid(any(UUID.class))).thenReturn(Optional.of(supplier));
        final var supplierFromService = service.get(supplier.getUuid());
        Assertions.assertEquals(supplier, supplierFromService);
    }

    @Test
    @DisplayName("Should not get non existent Supplier")
    void shouldNotGetNonExistentSupplier() {
        Mockito.when(gateway.findByUuid(any(UUID.class))).thenReturn(Optional.empty());
        final var supplierUUID = UUID.randomUUID();
        final var exception = Assertions.assertThrowsExactly(BusinessException.class, () -> service.get(supplierUUID));
        Assertions.assertEquals(ENTITY_NOT_FOUND_BY_ID.makeMessage("Fornecedor",supplierUUID),
                exception.getMessage());
    }
    
}
