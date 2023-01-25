package com.nu.stock.service;

import com.google.gson.GsonBuilder;
import com.nu.stock.domain.StockMarketOperation;
import com.nu.stock.util.StockMarketOpDeserializer;

import java.util.List;
import java.util.function.BiConsumer;

public class AggregatorProcessor {
    private final Aggregator aggregator;
    private final List<String> jsonStrings;
    private final GsonBuilder gsonBuilder = new GsonBuilder();

    public AggregatorProcessor(Aggregator aggregator, List<String> jsonStrings) {
        this.aggregator = aggregator;
        this.jsonStrings = jsonStrings;
    }

    public List<Number> run() {
        gsonBuilder.registerTypeAdapter(StockMarketOperation.class, new StockMarketOpDeserializer());
        for (String json: jsonStrings) {
            addStockMarketOp.accept(aggregator, json);
        }

        /* calculate tax */
        return aggregator.calculate();
    }

    /**
     * Functional bi-function to add stock operations to aggregator.
     */
    BiConsumer<Aggregator, String> addStockMarketOp = (a,b) ->
        a.add(gsonBuilder.create().fromJson(b, StockMarketOperation.class));
}
