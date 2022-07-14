package com.github.victorskg.domain.validator;

import static com.github.victorskg.common.exception.FieldValidatorExceptionMessage.NOT_EMPTY_TEXT;

import java.util.stream.Stream;

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

    private static Stream<Arguments> nameArguments() {
        final var productNameNotEmptyMessage = MessageReader.read("product.name.notEmpty");
        return Stream.of(
            Arguments.of(null, productNameNotEmptyMessage),
            Arguments.of(" ", productNameNotEmptyMessage),
            Arguments.of("", productNameNotEmptyMessage)
        );
    }

    @MethodSource("nameArguments")
    @DisplayName("Should not create Product with invalid name")
    @ParameterizedTest(name = "Should not create Product with invalid name: {0}")
    void shouldNotCreateProductWithInvalidName(final String name, final String expectedMessage) {
        final var exception = Assertions.assertThrowsExactly(FieldValidatorException.class,
                () -> Product.of(name, "Description", ProductCategory.of("Category")));
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Should not create Product with invalid category")
    void shouldNotCreateProductWithInvalidCategory() {
        final var productCategoryNotNullMessage = MessageReader.read("product.category.notNull");
        final var exception = Assertions.assertThrowsExactly(FieldValidatorException.class,
                () -> Product.of("Name", "Description", null));
        Assertions.assertEquals(productCategoryNotNullMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Should not create Product with invalid description")
    void shouldNotCreateProductWithInvalidDescription() {
        final var exception = Assertions.assertThrowsExactly(FieldValidatorException.class,
                () -> Product.of("Name", "", ProductCategory.of("Category")));
        Assertions.assertEquals(NOT_EMPTY_TEXT.makeMessage("Descrição"), exception.getMessage());
    }

}