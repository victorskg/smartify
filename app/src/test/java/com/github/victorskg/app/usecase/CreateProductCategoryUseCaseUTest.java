package com.github.victorskg.app.usecase;

import com.github.victorskg.app.repository.ProductCategoryRepository;
import com.github.victorskg.core.domain.ProductCategory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CreateProductCategoryUseCaseUTest {

    private final ProductCategoryRepository repository = Mockito.mock(ProductCategoryRepository.class);
    private final CreateProductCategoryUseCase useCase = new CreateProductCategoryUseCase(repository);

    @Test
    @DisplayName("Should create Product Category")
    void shouldCreateProductCategory() {
        final var productCategory = ProductCategory.of("Category");
        Mockito.when(repository.create(productCategory)).thenReturn(productCategory);
        final var createdProductCategory = useCase.execute(productCategory);
        Assertions.assertEquals(productCategory, createdProductCategory);
    }

}
