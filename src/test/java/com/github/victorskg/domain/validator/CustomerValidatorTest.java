package com.github.victorskg.domain.validator;

import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.victorskg.common.exception.FieldValidatorException;
import com.github.victorskg.domain.customer.Customer;
import com.github.victorskg.util.MessageReader;

class CustomerValidatorTest {

    private static Stream<Arguments> nameArguments() {
        final var customerNameNotEmptyMessage = MessageReader.read("customer.name.notBlank");
        final var customerNameSizeMessage = MessageReader.read("customer.name.size");
        return Stream.of(
            Arguments.of(null, customerNameNotEmptyMessage),
            Arguments.of("   ", customerNameNotEmptyMessage),
            Arguments.of(RandomStringUtils.randomAlphabetic(2), customerNameSizeMessage),
            Arguments.of(RandomStringUtils.randomAlphabetic(256), customerNameSizeMessage)
        );
    }

    private static Stream<Arguments> phoneArguments() {
        final var phonePatternMessage = MessageReader.read("phone.value.pattern");
        return Stream.of(
            Arguments.of("99999999", phonePatternMessage),
            Arguments.of("999999999", phonePatternMessage)
        );
    }

    @MethodSource("nameArguments")
    @DisplayName("Should not create Customer with invalid name")
    @ParameterizedTest(name = "Should not create Customer with invalid name: {0}")
    void shouldNotCreateCustomerWithInvalidName(final String name, final String expectedMessage) {
        final var exception = Assertions.assertThrows(FieldValidatorException.class,
                () -> Customer.builder().name(name).phone("999999999999").build());
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

    @MethodSource("phoneArguments")
    @DisplayName("Should not create Customer with invalid phone")
    @ParameterizedTest(name = "Should not create Customer with invalid phone: {0}")
    void shouldNotCreateCustomerWithInvalidPhone(final String phone, final String expectedMessage) {
        final var exception = Assertions.assertThrows(FieldValidatorException.class,
                () -> Customer.builder().name("Customer").phone(phone).build());
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

}
