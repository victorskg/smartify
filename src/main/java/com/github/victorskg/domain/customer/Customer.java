package com.github.victorskg.domain.customer;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.github.victorskg.domain.BaseDomain;
import com.github.victorskg.domain.vo.Phone;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseDomain<Customer> {

    @NotBlank(message = "{customer.name.notBlank}")
    @Size(message = "{customer.name.size}", min = 3, max = 255)
    private String name;

    @Valid
    private Phone phone;

    @Builder
    private Customer(final UUID uuid, final String name, final String phone) {
        super(uuid);
        this.name = name;
        this.phone = new Phone(phone);
        validateSelf();
    }

}
