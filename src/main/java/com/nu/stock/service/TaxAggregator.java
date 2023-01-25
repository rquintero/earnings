package com.nu.stock.service;

import com.nu.stock.domain.StockMarketOperation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TaxAggregator extends Aggregator {
    private BigDecimal boughtAmount = BigDecimal.ZERO;
    private long stocks = 0;
    private final BigDecimal taxRate = BigDecimal.valueOf(20).divide(BigDecimal.valueOf(100));

    @Override
    protected List<Number> calculate() {
        List<Number> retList = new ArrayList<>();
        for (StockMarketOperation sop: this.operations) {
            BigDecimal tax = BigDecimal.ZERO;
            switch (sop.getOperation()) {
                case BUY:
                    addBoughtAmount(sop);
                    addStocks(sop);
                    break;
                case SELL:
                    BigDecimal soldAmount = getSoldAmount(sop);
                    BigDecimal weightedAveragePrice = getWeightedAveragePrice();

                    /* Determine relationship between unit cost and average */
                    int compare = sop.getUnitCost().compareTo(weightedAveragePrice);
                    if (compare == 0) {
                        break;
                    }
                    BigDecimal avgBoughtAmount = getAvgBoughtAmount(sop, weightedAveragePrice);
                    if(compare >0) {
                        BigDecimal profit = soldAmount.subtract(avgBoughtAmount);

                        /* Only in case sold unit cost greater than average bought unit cost, try to get the tax */
                        tax = getTax(tax, soldAmount, profit);
                        break;
                    }
                    if (compare <0) {
                        addToLoss(soldAmount, avgBoughtAmount);
                        break;
                    }
                    break;
            }
            retList.add(tax);
        }
        return retList;
    }

    private void addToLoss(BigDecimal soldAmount, BigDecimal avgBoughtAmount) {
        BigDecimal aLoss = avgBoughtAmount.subtract(soldAmount);
        this.loss = this.loss.add(aLoss);
    }

    private BigDecimal getTax(BigDecimal tax, BigDecimal soldAmount, BigDecimal profit) {

        /* minus loss */
        BigDecimal netValue = profit.subtract(this.loss);
        if (netValue.doubleValue() >= 0d) {
            if (doesItPayTaxes.test(soldAmount)) {
                tax = netValue.multiply(taxRate).setScale(2, RoundingMode.UNNECESSARY);
            }
            this.loss = BigDecimal.ZERO;
        } else {
            this.loss = this.loss.subtract(netValue.abs());
        }
        return tax;
    }

    private BigDecimal getAvgBoughtAmount(StockMarketOperation sop, BigDecimal weightedAveragePrice) {
        BigDecimal avgBoughtAmount = weightedAveragePrice
                .multiply(BigDecimal.valueOf(sop.getQuantity().longValue()));
        return avgBoughtAmount;
    }

    private BigDecimal getWeightedAveragePrice() {
        BigDecimal weightedAveragePrice = boughtAmount
                .divide(BigDecimal.valueOf(stocks), 2, RoundingMode.UNNECESSARY);
        return weightedAveragePrice;
    }

    private BigDecimal getSoldAmount(StockMarketOperation sop) {
        BigDecimal soldAmount = sop.getUnitCost().multiply(BigDecimal
                .valueOf(sop.getQuantity().longValue()));
        return soldAmount;
    }

    private void addStocks(StockMarketOperation sop) {
        stocks+= sop.getQuantity().longValue();
    }

    private void addBoughtAmount(StockMarketOperation sop) {
        boughtAmount = boughtAmount.add(sop.getUnitCost()
                .multiply(BigDecimal.valueOf(sop.getQuantity().longValue())));
    }

    Predicate<BigDecimal> doesItPayTaxes = bigDecimal -> bigDecimal.doubleValue() > 20000;
}
