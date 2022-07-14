package com.github.victorskg.app.usecase;

import static com.github.victorskg.core.domain.exception.BusinessExceptionMessage.ENTITY_NOT_FOUND_BY_ID;

import java.util.Optional;
import java.util.UUID;

import com.github.victorskg.app.repository.ProductRepository;
import com.github.victorskg.core.domain.Product;
import com.github.victorskg.core.domain.ProductCategory;
import com.github.victorskg.core.domain.exception.BusinessException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UpdateProductUseCaseUTest {
    
    private final ProductRepository repository = Mockito.mock(ProductRepository.class);
    private final UpdateProductUseCase useCase = new UpdateProductUseCase(repository);

    @Test
    @DisplayName("Should update Product")
    void shouldUpdateProduct() {
        final var product = Product.of(UUID.randomUUID(), "Product", "Description", ProductCategory.of("Category"));
        Mockito.when(repository.findByUuid(product.getUuid())).thenReturn(Optional.of(product));
        Mockito.when(repository.update(product)).thenReturn(product);
        final var updatedProduct = useCase.execute(product);
        Assertions.assertEquals(updatedProduct, product);
    }

    @Test
    @DisplayName("Should not update Product when it doesn't exist")
    void shouldNotUpdateProductWhenItDoesNotExist() {
        final var product = Product.of(UUID.randomUUID(), "Product", "Description", ProductCategory.of("Category"));
        Mockito.when(repository.findByUuid(product.getUuid())).thenReturn(Optional.empty());
        final var exception = Assertions.assertThrows(BusinessException.class,
                () -> useCase.execute(product));
        Assertions.assertEquals(ENTITY_NOT_FOUND_BY_ID.makeMessage("Produto", product.getUuid()), exception.getMessage());
    }

}