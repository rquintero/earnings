package com.nu.stock.service;

import com.nu.stock.util.MotherOfObjects;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AggregatorProcessorTest {

    @Test
    void case6ok() {
        List<String> list = MotherOfObjects.getCase6();
        Aggregator aggregator = new TaxAggregator();
        AggregatorProcessor aggregatorProcessor = new AggregatorProcessor(aggregator, list);
        List<Number> numbers = aggregatorProcessor.run();
        assertTrue(Arrays.asList(0,0,0,0,3000).equals(toIntList.apply(numbers)));
        assertFalse(Arrays.asList(1d,0d,0d,0d,3000d).equals(toDoubleList.apply(numbers)));
    }

    @Test
    void case1ok() {
        List<String> list = MotherOfObjects.getCase1();
        Aggregator aggregator = new TaxAggregator();
        AggregatorProcessor aggregatorProcessor = new AggregatorProcessor(aggregator, list);
        List<Number> numbers = aggregatorProcessor.run();
        assertTrue(Arrays.asList(0,0,0).equals(toIntList.apply(numbers)));
        assertFalse(Arrays.asList(1d,0d,0d).equals(toDoubleList.apply(numbers)));
    }

    @Test
    void case1WithStartOfText() {
        List<String> list = MotherOfObjects.getCase1_1();
        Aggregator aggregator = new TaxAggregator();
        AggregatorProcessor aggregatorProcessor = new AggregatorProcessor(aggregator, list);
        List<Number> numbers = aggregatorProcessor.run();
        assertTrue(Arrays.asList(0,0,0).equals(toIntList.apply(numbers)));
        assertFalse(Arrays.asList(1d,0d,0d).equals(toDoubleList.apply(numbers)));
    }

    @Test
    void case2ok() {
        List<String> list = MotherOfObjects.getCase2();
        Aggregator aggregator = new TaxAggregator();
        AggregatorProcessor aggregatorProcessor = new AggregatorProcessor(aggregator, list);
        List<Number> numbers = aggregatorProcessor.run();
        assertTrue(Arrays.asList(0,10000,0).equals(toIntList.apply(numbers)));
        assertFalse(Arrays.asList(0d,10000.1d,0d).equals(toDoubleList.apply(numbers)));
    }

    @Test
    void case3ok() {
        List<String> list = MotherOfObjects.getCase3();
        Aggregator aggregator = new TaxAggregator();
        AggregatorProcessor aggregatorProcessor = new AggregatorProcessor(aggregator, list);
        List<Number> numbers = aggregatorProcessor.run();
        assertTrue(Arrays.asList(0,0,5000).equals(toIntList.apply(numbers)));
        assertFalse(Arrays.asList(0d,0d,5000.1d).equals(toDoubleList.apply(numbers)));
    }

    @Test
    void case4ok() {
        List<String> list = MotherOfObjects.getCase4();
        Aggregator aggregator = new TaxAggregator();
        AggregatorProcessor aggregatorProcessor = new AggregatorProcessor(aggregator, list);
        List<Number> numbers = aggregatorProcessor.run();
        assertTrue(Arrays.asList(0,0,0).equals(toIntList.apply(numbers)));
    }

    @Test
    void case4WithJsonObjectNotWellFormedOk() {
        List<String> list = MotherOfObjects.getCase4_1();
        Aggregator aggregator = new TaxAggregator();
        AggregatorProcessor aggregatorProcessor = new AggregatorProcessor(aggregator, list);
        List<Number> numbers = aggregatorProcessor.run();
        assertTrue(Arrays.asList(0d,0d).equals(toDoubleList.apply(numbers)));
    }

    @Test
    void case5ok() {
        List<String> list = MotherOfObjects.getCase5();
        Aggregator aggregator = new TaxAggregator();
        AggregatorProcessor aggregatorProcessor = new AggregatorProcessor(aggregator, list);
        List<Number> numbers = aggregatorProcessor.run();
        assertTrue(Arrays.asList(0,0,0,10000).equals(toIntList.apply(numbers)));
        assertFalse(Arrays.asList(0d,0d,0d,10000.001d).equals(toDoubleList.apply(numbers)));
    }

    @Test
    void case7BuySellBuySellWithDecimals() {
        List<String> list = MotherOfObjects.getCase7();
        Aggregator aggregator = new TaxAggregator();
        AggregatorProcessor aggregatorProcessor = new AggregatorProcessor(aggregator, list);
        List<Number> numbers = aggregatorProcessor.run();
        numbers.stream().forEach(System.out::println);
        assertTrue(Arrays.asList(0,0,0,12000).equals(toIntList.apply(numbers)));
        assertFalse(Arrays.asList(0d,0d,0d,12500.001d).equals(toDoubleList.apply(numbers)));
    }

    Function<List<Number>, List<Double>> toDoubleList
            = a -> a.stream().mapToDouble(Number::doubleValue).boxed().collect(Collectors.toList());

    Function<List<Number>, List<Integer>> toIntList
            = a -> a.stream().mapToInt(Number::intValue).boxed().collect(Collectors.toList());
}
