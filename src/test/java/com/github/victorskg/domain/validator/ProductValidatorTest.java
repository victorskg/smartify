package com.github.victorskg.domain.validator;

import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.victorskg.common.exception.FieldValidatorException;
import com.github.victorskg.domain.product.Product;
import com.github.victorskg.domain.product.category.ProductCategory;
import com.github.victorskg.util.MessageReader;

class ProductValidatorTest {

    private final ProductCategory productCategory = ProductCategory.builder().name("Category").build();

    private static Stream<Arguments> nameArguments() {
        final var productNameNotEmptyMessage = MessageReader.read("product.name.notEmpty");
        return Stream.of(
            Arguments.of(null, productNameNotEmptyMessage),
            Arguments.of(" ", productNameNotEmptyMessage),
            Arguments.of("", productNameNotEmptyMessage)
        );
    }

    private static Stream<Arguments> descriptionArguments() {
        final var productDescriptionOptionalNotEmptyMessage = MessageReader.read("product.description.optionalNotEmpty");
        return Stream.of(
            Arguments.of("", productDescriptionOptionalNotEmptyMessage),
            Arguments.of(" a ", productDescriptionOptionalNotEmptyMessage),
            Arguments.of(RandomStringUtils.randomAlphabetic(256), productDescriptionOptionalNotEmptyMessage)
        );
    }

    @MethodSource("nameArguments")
    @DisplayName("Should not create Product with invalid name")
    @ParameterizedTest(name = "Should not create Product with invalid name: {0}")
    void shouldNotCreateProductWithInvalidName(final String name, final String expectedMessage) {
        final var exception = Assertions.assertThrowsExactly(FieldValidatorException.class,
                () -> Product.builder().name(name).category(productCategory).build());
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Should not create Product with invalid category")
    void shouldNotCreateProductWithInvalidCategory() {
        final var productCategoryNotNullMessage = MessageReader.read("product.category.notNull");
        final var exception = Assertions.assertThrowsExactly(FieldValidatorException.class,
                () -> Product.builder().name("Name").build());
        Assertions.assertEquals(productCategoryNotNullMessage, exception.getMessage());
    }

    @MethodSource("descriptionArguments")
    @DisplayName("Should not create Product with invalid description")
    @ParameterizedTest(name = "Should not create Product with invalid description: {0}")
    void shouldNotCreateProductWithInvalidDescription(final String description, final String expectedMessage) {
        final var exception = Assertions.assertThrowsExactly(FieldValidatorException.class,
                () -> Product.builder().name("Name").category(productCategory).description(description).build());
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

}
