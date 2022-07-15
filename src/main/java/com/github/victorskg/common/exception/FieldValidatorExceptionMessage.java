package com.github.victorskg.common.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FieldValidatorExceptionMessage {

    NON_NULL("O campo \"%s\" é obrigatório."),
    TEXT_SIZE("O campo \"%s\" deve ter %d caractéres."),
    MIN_TEXT_SIZE("O campo \"%s\" deve ter no mínimo %d caractéres."),
    MAX_TEXT_SIZE("O campo \"%s\" deve ter no máximo %d caractéres."),
    NOT_EMPTY_TEXT("O campo \"%s\" é obrigatório."),
    TEXT_PATTERN("O campo \"%s\" está fora do padrão definido."),
    POSITIVE_AND_BIGGER_THAN_ZERO("O campo \"%s\" deve ser maior que zero.");

    private final String message;

    public final String makeMessage(Object... args) {
        return String.format(this.message, args);
    }

}
