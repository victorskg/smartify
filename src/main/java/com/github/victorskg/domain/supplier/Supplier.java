package com.github.victorskg.domain.supplier;

import java.util.UUID;

import com.github.victorskg.domain.BaseDomain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Supplier extends BaseDomain<Supplier> {

    private String name;

    private Supplier(final String name) {
        super(null);
        this.name = name;
        validateSelf();
    }

    private Supplier(final UUID uuid, final String name) {
        super(uuid);
        this.name = name;
        validateSelf();
    }

    public static Supplier of(final String name) {
        return SupplierValidator.validate(new Supplier(name));
    }

    public static Supplier of(final UUID uuid, final String name) {
        return SupplierValidator.validate(new Supplier(uuid, name));
    }

}