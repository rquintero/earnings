package com.nu.stock.service;

import com.nu.stock.domain.StockMarketOperation;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Aggregator {
    protected final List<StockMarketOperation> operations;
    protected BigDecimal loss;

    public Aggregator() {
        this.operations = new CopyOnWriteArrayList<>();
        this.loss = BigDecimal.ZERO;;
    }

    public void add(StockMarketOperation operation) {
        operations.add(operation);
    }

    protected abstract List<Number> calculate();
}
