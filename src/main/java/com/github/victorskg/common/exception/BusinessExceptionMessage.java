package com.github.victorskg.common.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BusinessExceptionMessage {

    ENTITY_NOT_FOUND_BY_ID("Não foi possível encontrar '%s' de id '%s'.");

    private final String message;

    public String makeMessage(Object... args) {
        return String.format(this.message, args);
    }

}
