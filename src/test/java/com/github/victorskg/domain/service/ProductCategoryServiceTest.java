package com.github.victorskg.domain.service;

import static com.github.victorskg.common.exception.BusinessExceptionMessage.ENTITY_NOT_FOUND_BY_ID;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.github.victorskg.common.exception.BusinessException;
import com.github.victorskg.domain.product.category.ProductCategory;
import com.github.victorskg.domain.product.category.ProductCategoryGateway;
import com.github.victorskg.domain.product.category.ProductCategoryService;

class ProductCategoryServiceTest {

    private final ProductCategoryGateway gateway;
    private final ProductCategoryService service;

    ProductCategoryServiceTest() {
        gateway = Mockito.mock(ProductCategoryGateway.class);
        service = new ProductCategoryService(gateway);
    }

    @Test
    @DisplayName("Should create Product Category")
    void shouldCreateProductCategory() {
        Mockito.when(gateway.create(any(ProductCategory.class))).then(returnsFirstArg());
        final var productCategory = ProductCategory.builder().name("Product Category").build();
        final var savedProductCategory = service.create(productCategory);
        Assertions.assertEquals(productCategory, savedProductCategory);
    }

    @Test
    @DisplayName("Should update Product Category")
    void shouldUpdateProductCategory() {
        Mockito.when(gateway.update(any(ProductCategory.class))).then(returnsFirstArg());
        final var productCategory = ProductCategory.builder().uuid(UUID.randomUUID()).name("Product Category Updated")
                .build();
        Mockito.when(gateway.findByUuid(any(UUID.class))).thenReturn(Optional.of(productCategory));
        final var savedProductCategory = service.update(productCategory);
        Assertions.assertEquals(productCategory, savedProductCategory);
    }

    @Test
    @DisplayName("Should not update non existent Product Category")
    void shouldNotUpdateNonExistentProductCategory() {
        Mockito.when(gateway.findByUuid(any(UUID.class))).thenReturn(Optional.empty());
        final var productCategory = ProductCategory.builder().uuid(UUID.randomUUID()).name("Product Category Updated")
                .build();
        final var exception = Assertions.assertThrowsExactly(BusinessException.class,
                () -> service.update(productCategory));
        Assertions.assertEquals(ENTITY_NOT_FOUND_BY_ID.makeMessage("Categoria de Produto", productCategory.getUuid()),
                exception.getMessage());
    }

    @Test
    @DisplayName("Should get Product Category")
    void shouldGetProductCategory() {
        final var productCategory = ProductCategory.builder().uuid(UUID.randomUUID()).name("Product Category").build();
        Mockito.when(gateway.findByUuid(any(UUID.class))).thenReturn(Optional.of(productCategory));
        final var productCategoryFromService = service.get(productCategory.getUuid());
        Assertions.assertEquals(productCategory, productCategoryFromService);
    }

    @Test
    @DisplayName("Should not get non existent Product Category")
    void shouldNotGetNonExistentProductCategory() {
        Mockito.when(gateway.findByUuid(any(UUID.class))).thenReturn(Optional.empty());
        final var productCategoryUUID = UUID.randomUUID();
        final var exception = Assertions.assertThrowsExactly(BusinessException.class,
                () -> service.get(productCategoryUUID));
        Assertions.assertEquals(ENTITY_NOT_FOUND_BY_ID.makeMessage("Categoria de Produto", productCategoryUUID),
                exception.getMessage());
    }

}
