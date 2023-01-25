package com.nu.stock.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

public class StockMarketOperation {
    private final OperationEnum operation;
    private final BigDecimal unitCost;
    private final BigInteger quantity;

    public StockMarketOperation(OperationEnum operation, BigDecimal unitCost, BigInteger quantity) {
        this.operation = operation;
        this.unitCost = unitCost;
        this.quantity = quantity;
    }

    public OperationEnum getOperation() {
        return operation;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    @Override
    public String toString() {
        return "StockMarketOperation{" +
                "operation=" + operation +
                ", unitCost=" + unitCost +
                ", quantity=" + quantity +
                '}';
    }

    public BigInteger getQuantity() {
        return quantity;
    }
}
