package com.github.victorskg.domain.supplier;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.github.victorskg.domain.BaseDomain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Supplier extends BaseDomain<Supplier> {

    @NotBlank(message = "{supplier.name.notBlank}")
    @Size(message = "{supplier.name.size}", min = 3, max = 255)
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
        return new Supplier(name);
    }

    public static Supplier of(final UUID uuid, final String name) {
        return new Supplier(uuid, name);
    }

}