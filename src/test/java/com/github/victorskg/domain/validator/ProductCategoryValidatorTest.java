package com.github.victorskg.domain.validator;

import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.victorskg.common.exception.FieldValidatorException;
import com.github.victorskg.domain.product.category.ProductCategory;
import com.github.victorskg.util.MessageReader;

class ProductCategoryValidatorTest {

    private static Stream<Arguments> nameArguments() {
        final var productcategoryNameNotEmptyMessage = MessageReader.read("productCategory.name.notBlank");
        final var productcategoryNameSizeMessage = MessageReader.read("productCategory.name.size");
        return Stream.of(
            Arguments.of(null, productcategoryNameNotEmptyMessage),
            Arguments.of("   ", productcategoryNameNotEmptyMessage),
            Arguments.of(RandomStringUtils.randomAlphabetic(2), productcategoryNameSizeMessage),
            Arguments.of(RandomStringUtils.randomAlphabetic(256), productcategoryNameSizeMessage)
        );
    }

    @MethodSource("nameArguments")
    @DisplayName("Should not create Product Category with invalid name")
    @ParameterizedTest(name = "Should not create Product Category with invalid name: {0}")
    void shouldNotCreateProductCategoryWithInvalidName(final String name, final String expectedMessage) {
        final var exception = Assertions.assertThrows(FieldValidatorException.class,
                () -> ProductCategory.builder().name(name).build());
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

}
