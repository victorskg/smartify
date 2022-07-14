package com.github.victorskg.app.usecase;

import com.github.victorskg.app.repository.ProductRepository;
import com.github.victorskg.core.domain.Product;
import com.github.victorskg.core.domain.ProductCategory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CreateProductUseCaseUTest {

    private final ProductRepository repository = Mockito.mock(ProductRepository.class);
    private final CreateProductUseCase useCase = new CreateProductUseCase(repository);

    @Test
    @DisplayName("Should create Product")
    void shouldCreateProduct() {
        final var product = Product.of("Product", "Description", ProductCategory.of("Category"));
        Mockito.when(repository.create(product)).thenReturn(product);
        final var createdProduct = useCase.execute(product);
        Assertions.assertEquals(product, createdProduct);
    }
    
}