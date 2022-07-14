package com.github.victorskg.domain.customer;

import java.util.UUID;

import com.github.victorskg.domain.BaseDomain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseDomain<Customer> {

    private String name;
    private String phone;

    private Customer(final String name, final String phone) {
        super(null);
        this.name = name;
        this.phone = phone;
        validateSelf();
    }

    private Customer(final UUID uuid, final String name, final String phone) {
        super(uuid);
        this.name = name;
        this.phone = phone;
        validateSelf();
    }

    public static Customer of(final String name, final String phone) {
        return CustomerValidator.validate(new Customer(name, phone));
    }

    public static Customer of(final UUID uuid, final String name, final String phone) {
        return CustomerValidator.validate(new Customer(uuid, name, phone));
    }

}
