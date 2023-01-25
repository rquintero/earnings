package com.nu.stock.util;

import com.google.gson.*;
import com.nu.stock.domain.OperationEnum;
import com.nu.stock.domain.StockMarketOperation;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;

public class StockMarketOpDeserializer implements JsonDeserializer<StockMarketOperation> {

    public static final String OPERATION = "operation";
    public static final String UNIT_COST = "unit-cost";
    public static final String QUANTITY = "quantity";

    @Override
    public StockMarketOperation deserialize(JsonElement jElement, Type typeOfT,
                                            JsonDeserializationContext context) throws JsonParseException {
        JsonObject jObject = jElement.getAsJsonObject();
        String op = jObject.get(OPERATION).getAsString();
        BigDecimal cost = jObject.get(UNIT_COST).getAsBigDecimal();
        BigInteger qty = jObject.get(QUANTITY).getAsBigInteger();
        return new StockMarketOperation(OperationEnum.valueOf(op.toUpperCase(Locale.ROOT)), cost, qty);
    }
}
