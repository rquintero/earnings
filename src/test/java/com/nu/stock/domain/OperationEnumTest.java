package com.nu.stock.domain;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class OperationEnumTest {

    @Test
    void valueOf() {
        assertEquals(OperationEnum.valueOf("BUY"), OperationEnum.BUY);
        assertEquals(OperationEnum.valueOf("buy".toUpperCase(Locale.ROOT)), OperationEnum.BUY);
        assertEquals(OperationEnum.valueOf("SELL"), OperationEnum.SELL);
        assertEquals(OperationEnum.valueOf("sell".toUpperCase(Locale.ROOT)), OperationEnum.SELL);
    }
}