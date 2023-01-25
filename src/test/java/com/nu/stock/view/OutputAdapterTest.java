package com.nu.stock.view;

import com.nu.stock.service.Aggregator;
import com.nu.stock.service.AggregatorProcessor;
import com.nu.stock.service.TaxAggregator;
import com.nu.stock.util.MotherOfObjects;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class OutputAdapterTest {

    @Test
    void case6ok() {
        List<String> list = MotherOfObjects.getCase6();
        Aggregator aggregator = new TaxAggregator();
        AggregatorProcessor aggregatorProcessor = new AggregatorProcessor(aggregator, list);
        List<Number> numbers = aggregatorProcessor.run();
        OutputAdapter outputAdapter = new OutputAdapter();
        String taxes = outputAdapter.getTaxes(numbers);
        assertEquals("[{\"tax\": 0},{\"tax\": 0},{\"tax\": 0},{\"tax\": 0},{\"tax\": 3000}]", taxes);
    }


    @Test
    void case6nok() {
        List<String> list = MotherOfObjects.getCase6();
        Aggregator aggregator = new TaxAggregator();
        AggregatorProcessor aggregatorProcessor = new AggregatorProcessor(aggregator, list);
        List<Number> numbers = aggregatorProcessor.run();
        OutputAdapter outputAdapter = new OutputAdapter();
        String taxes = outputAdapter.getTaxes(numbers);
        assertNotEquals("[{\"tax\":0},{\"tax\":0},{\"tax\":0},{\"tax\":0},{\"tax\":3000}]", taxes);
    }

    @Test
    void case1ok() {
        List<String> list = MotherOfObjects.getCase1();
        Aggregator aggregator = new TaxAggregator();
        AggregatorProcessor aggregatorProcessor = new AggregatorProcessor(aggregator, list);
        List<Number> numbers = aggregatorProcessor.run();
        OutputAdapter outputAdapter = new OutputAdapter();
        String taxes = outputAdapter.getTaxes(numbers);
        assertEquals("[{\"tax\": 0},{\"tax\": 0},{\"tax\": 0}]", taxes);
    }

    @Test
    void case2ok() {
        List<String> list = MotherOfObjects.getCase2();
        Aggregator aggregator = new TaxAggregator();
        AggregatorProcessor aggregatorProcessor = new AggregatorProcessor(aggregator, list);
        List<Number> numbers = aggregatorProcessor.run();
        OutputAdapter outputAdapter = new OutputAdapter();
        String taxes = outputAdapter.getTaxes(numbers);
        assertEquals("[{\"tax\": 0},{\"tax\": 10000},{\"tax\": 0}]", taxes);
    }

    @Test
    void case3ok() {
        List<String> list = MotherOfObjects.getCase3();
        Aggregator aggregator = new TaxAggregator();
        AggregatorProcessor aggregatorProcessor = new AggregatorProcessor(aggregator, list);
        List<Number> numbers = aggregatorProcessor.run();
        OutputAdapter outputAdapter = new OutputAdapter();
        String taxes = outputAdapter.getTaxes(numbers);
        assertEquals("[{\"tax\": 0},{\"tax\": 0},{\"tax\": 5000}]", taxes);
    }

    @Test
    void case4ok() {
        List<String> list = MotherOfObjects.getCase4();
        Aggregator aggregator = new TaxAggregator();
        AggregatorProcessor aggregatorProcessor = new AggregatorProcessor(aggregator, list);
        List<Number> numbers = aggregatorProcessor.run();
        OutputAdapter outputAdapter = new OutputAdapter();
        String taxes = outputAdapter.getTaxes(numbers);
        assertEquals("[{\"tax\": 0},{\"tax\": 0},{\"tax\": 0}]", taxes);
        assertNotEquals("[{\"tax\": 0},{\"tax\": 0},{\"tax\": 0}]\n", taxes);
    }

    @Test
    void case4WithJsonObjectNotWellFormedOk() {
        List<String> list = MotherOfObjects.getCase4_1();
        Aggregator aggregator = new TaxAggregator();
        AggregatorProcessor aggregatorProcessor = new AggregatorProcessor(aggregator, list);
        List<Number> numbers = aggregatorProcessor.run();
        OutputAdapter outputAdapter = new OutputAdapter();
        String taxes = outputAdapter.getTaxes(numbers);
        assertEquals("[{\"tax\": 0},{\"tax\": 0}]", taxes);
        assertNotEquals("[{\"tax\": 0},{\"tax\": 0}, {\"tax\": 0}]", taxes);
    }

    @Test
    void case5ok() {
        List<String> list = MotherOfObjects.getCase5();
        Aggregator aggregator = new TaxAggregator();
        AggregatorProcessor aggregatorProcessor = new AggregatorProcessor(aggregator, list);
        List<Number> numbers = aggregatorProcessor.run();
        OutputAdapter outputAdapter = new OutputAdapter();
        String taxes = outputAdapter.getTaxes(numbers);
        assertEquals("[{\"tax\": 0},{\"tax\": 0},{\"tax\": 0},{\"tax\": 10000}]", taxes);
    }

    @Test
    void case7ok() {
        List<String> list = MotherOfObjects.getCase7();
        Aggregator aggregator = new TaxAggregator();
        AggregatorProcessor aggregatorProcessor = new AggregatorProcessor(aggregator, list);
        List<Number> numbers = aggregatorProcessor.run();
        OutputAdapter outputAdapter = new OutputAdapter();
        String taxes = outputAdapter.getTaxes(numbers);
        assertEquals("[{\"tax\": 0},{\"tax\": 0},{\"tax\": 0},{\"tax\": 12000}]", taxes);
    }
}
