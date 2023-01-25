package com.nu.stock.view;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OutputAdapter {
    private static final String TAX_TEMPLATE = "{\"tax\": %d}";
    private static final Function<Number, String> consoleReader = (a) -> String.format(TAX_TEMPLATE, a.intValue());
    public static final String O_BRACKET = "[";
    public static final String C_BRACKET = "]";
    public static final String COMMA = ",";

    public void write(List<Number> numbers) {
        String taxes = getTaxes(numbers);
        System.out.println(taxes);
    }

    public String getTaxes(List<Number> numbers) {
        String taxes =
                O_BRACKET+
                    numbers.stream().map(consoleReader).collect(Collectors.joining(COMMA))
                +C_BRACKET;
        return taxes;
    }
}
