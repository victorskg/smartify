package com.github.victorskg.app.usecase;

import static com.github.victorskg.core.domain.exception.BusinessExceptionMessage.ENTITY_NOT_FOUND_BY_ID;

import java.util.Optional;
import java.util.UUID;

import com.github.victorskg.app.repository.ProductCategoryRepository;
import com.github.victorskg.core.domain.ProductCategory;
import com.github.victorskg.core.domain.exception.BusinessException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UpdateProductCategoryUseCaseUTest {
    
    private final ProductCategoryRepository repository = Mockito.mock(ProductCategoryRepository.class);
    private final UpdateProductCategoryUseCase useCase = new UpdateProductCategoryUseCase(repository);

    @Test
    @DisplayName("Should update Product Category")
    void shouldUpdateProductCategory() {
        final var productCategory = ProductCategory.of(UUID.randomUUID(), "Category");
        Mockito.when(repository.findByUuid(productCategory.getUuid())).thenReturn(Optional.of(productCategory));
        Mockito.when(repository.update(productCategory)).thenReturn(productCategory);
        final var updatedProductCategory = useCase.execute(productCategory);
        Assertions.assertEquals(updatedProductCategory, productCategory);
    }

    @Test
    @DisplayName("Should not update Product Category when it doesn't exist")
    void shouldNotUpdateProductCategoryWhenItDoesNotExist() {
        final var productCategory = ProductCategory.of(UUID.randomUUID(), "Category");
        Mockito.when(repository.findByUuid(productCategory.getUuid())).thenReturn(Optional.empty());
        final var exception = Assertions.assertThrows(BusinessException.class,
                () -> useCase.execute(productCategory));
        Assertions.assertEquals(ENTITY_NOT_FOUND_BY_ID.makeMessage("Categoria de Produto", productCategory.getUuid()), exception.getMessage());
    }

}
