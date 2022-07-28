package com.github.victorskg.domain.validator;

import static com.github.victorskg.common.exception.FieldValidatorExceptionMessage.NON_NULL;
import static com.github.victorskg.common.exception.FieldValidatorExceptionMessage.NOT_EMPTY_TEXT;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.victorskg.common.exception.FieldValidatorException;
import com.github.victorskg.domain.product.category.ProductCategory;

class ProductCategoryValidatorTest {

    private static Stream<Arguments> nameArguments() {
        return Stream.of(
            Arguments.of(null, NON_NULL.makeMessage("Nome")),
            Arguments.of(" ", NOT_EMPTY_TEXT.makeMessage("Nome"))
        );
    }

    @MethodSource("nameArguments")
    @DisplayName("Should not create Product Category with invalid name")
    @ParameterizedTest(name = "Should not create Product Category with invalid name: {0}")
    void shouldNotCreateProductCategoryWithInvalidName(final String name, final String expectedMessage) {
        final var exception = Assertions.assertThrows(FieldValidatorException.class,
                () -> ProductCategory.of(name));
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

}
