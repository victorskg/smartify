package com.github.victorskg.core.domain;

import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = PRIVATE)
public class Stock {
    
    private UUID uuid;
    private Product product;
    private Supplier supplier;
    private BigDecimal unitBuyPrice;
    private BigDecimal unitSellPrice;
    private int quantity;

}