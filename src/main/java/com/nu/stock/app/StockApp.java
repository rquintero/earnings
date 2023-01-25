package com.nu.stock.app;

import com.nu.stock.service.Aggregator;
import com.nu.stock.service.AggregatorProcessor;
import com.nu.stock.service.TaxAggregator;
import com.nu.stock.view.InputReader;
import com.nu.stock.view.OutputAdapter;

public class StockApp {
    private final static InputReader console = new InputReader();
    private final static Aggregator aggregator = new TaxAggregator();
    private final static OutputAdapter out = new OutputAdapter();
    public static void main(String[] args) {
        out.write(new AggregatorProcessor(aggregator, console.read()).run());
    }
}
