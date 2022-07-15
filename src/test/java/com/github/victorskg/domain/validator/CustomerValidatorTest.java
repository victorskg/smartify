package com.github.victorskg.domain.validator;

import static com.github.victorskg.common.exception.FieldValidatorExceptionMessage.NON_NULL;
import static com.github.victorskg.common.exception.FieldValidatorExceptionMessage.NOT_EMPTY_TEXT;
import static com.github.victorskg.common.exception.FieldValidatorExceptionMessage.TEXT_PATTERN;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.victorskg.common.exception.FieldValidatorException;
import com.github.victorskg.domain.customer.Customer;

class CustomerValidatorTest {

    private static Stream<Arguments> nameArguments() {
        return Stream.of(
                Arguments.of(null, NON_NULL.makeMessage("Nome")),
                Arguments.of(" ", NOT_EMPTY_TEXT.makeMessage("Nome"))
        );
    }

    private static Stream<Arguments> phoneArguments() {
        return Stream.of(
                Arguments.of("9999999", TEXT_PATTERN.makeMessage("Telefone")),
                Arguments.of("9999999999", TEXT_PATTERN.makeMessage("Telefone"))
        );
    }

    @MethodSource("nameArguments")
    @DisplayName("Should not create Customer with invalid name")
    @ParameterizedTest(name = "Should not create Customer with invalid name: {0}")
    void shouldNotCreateCustomerWithInvalidName(final String name, final String expectedMessage) {
        final var exception = Assertions.assertThrows(FieldValidatorException.class,
                () -> Customer.of(name, "999999999"));
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

    @MethodSource("phoneArguments")
    @DisplayName("Should not create Customer with invalid phone")
    @ParameterizedTest(name = "Should not create Customer with invalid phone: {0}")
    void shouldNotCreateCustomerWithInvalidPhone(final String phone, final String expectedMessage) {
        final var exception = Assertions.assertThrows(FieldValidatorException.class,
                () -> Customer.of("Customer", phone));
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

}