package com.github.victorskg.domain.validator;

import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.victorskg.common.exception.FieldValidatorException;
import com.github.victorskg.domain.supplier.Supplier;
import com.github.victorskg.util.MessageReader;

class SupplierValidatorTest {

    private static Stream<Arguments> nameArguments() {
        final var supplierNameNotEmptyMessage = MessageReader.read("supplier.name.notBlank");
        final var supplierNameSizeMessage = MessageReader.read("supplier.name.size");
        return Stream.of(
            Arguments.of(null, supplierNameNotEmptyMessage),
            Arguments.of("   ", supplierNameNotEmptyMessage),
            Arguments.of(RandomStringUtils.randomAlphabetic(2), supplierNameSizeMessage),
            Arguments.of(RandomStringUtils.randomAlphabetic(256), supplierNameSizeMessage)
        );
    }

    @MethodSource("nameArguments")
    @DisplayName("Should not create Supplier with invalid name")
    @ParameterizedTest(name = "Should not create Supplier with invalid name: {0}")
    void shouldNotCreateSupplierWithInvalidName(final String name, final String expectedMessage) {
        final var exception = Assertions.assertThrowsExactly(FieldValidatorException.class,
                () -> Supplier.of(name));
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }
    
}