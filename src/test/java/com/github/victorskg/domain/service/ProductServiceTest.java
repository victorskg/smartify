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
import com.github.victorskg.domain.product.Product;
import com.github.victorskg.domain.product.ProductGateway;
import com.github.victorskg.domain.product.ProductService;
import com.github.victorskg.domain.product.category.ProductCategory;

class ProductServiceTest {

    private final ProductGateway gateway;
    private final ProductService service;
    private final ProductCategory productCategory;

    ProductServiceTest() {
        gateway = Mockito.mock(ProductGateway.class);
        service = new ProductService(gateway);
        productCategory = ProductCategory.builder().name("Category").build();
    }

    @Test
    @DisplayName("Should create Product")
    void shouldCreateProduct() {
        Mockito.when(gateway.create(any(Product.class))).then(returnsFirstArg());
        final var product = Product.builder().name("Product").category(productCategory).build();
        final var savedProduct = service.create(product);
        Assertions.assertEquals(product, savedProduct);
    }

    @Test
    @DisplayName("Should update Product")
    void shouldUpdateProduct() {
        Mockito.when(gateway.update(any(Product.class))).then(returnsFirstArg());
        final var product = Product.builder().uuid(UUID.randomUUID()).name("Product Updated").category(productCategory)
                .build();
        Mockito.when(gateway.findByUuid(any(UUID.class))).thenReturn(Optional.of(product));
        final var savedProduct = service.update(product);
        Assertions.assertEquals(product, savedProduct);
    }

    @Test
    @DisplayName("Should not update non existent Product")
    void shouldNotUpdateNonExistentProduct() {
        Mockito.when(gateway.findByUuid(any(UUID.class))).thenReturn(Optional.empty());
        final var product = Product.builder().uuid(UUID.randomUUID()).name("Product Updated").category(productCategory)
                .build();
        final var exception = Assertions.assertThrowsExactly(BusinessException.class, () -> service.update(product));
        Assertions.assertEquals(ENTITY_NOT_FOUND_BY_ID.makeMessage("Produto", product.getUuid()),
                exception.getMessage());
    }

    @Test
    @DisplayName("Should get Product")
    void shouldGetProduct() {
        final var product = Product.builder().uuid(UUID.randomUUID()).name("Product").category(productCategory).build();
        Mockito.when(gateway.findByUuid(any(UUID.class))).thenReturn(Optional.of(product));
        final var productFromService = service.get(product.getUuid());
        Assertions.assertEquals(product, productFromService);
    }

    @Test
    @DisplayName("Should not get non existent Product")
    void shouldNotGetNonExistentProduct() {
        Mockito.when(gateway.findByUuid(any(UUID.class))).thenReturn(Optional.empty());
        final var productUUID = UUID.randomUUID();
        final var exception = Assertions.assertThrowsExactly(BusinessException.class, () -> service.get(productUUID));
        Assertions.assertEquals(ENTITY_NOT_FOUND_BY_ID.makeMessage("Produto", productUUID), exception.getMessage());
    }

}
