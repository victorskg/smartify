package com.github.victorskg.domain;

import java.util.UUID;

import com.github.victorskg.common.validator.SelfValidating;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "uuid", callSuper = false)
public abstract class BaseDomain<T> extends SelfValidating<T> {

    protected final UUID uuid;

    protected BaseDomain(final UUID uuid) {
        super();
        this.uuid = uuid;
    }
    
}
