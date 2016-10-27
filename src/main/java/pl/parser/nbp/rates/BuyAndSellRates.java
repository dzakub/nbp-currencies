package pl.parser.nbp.rates;

import org.apache.commons.lang3.ArrayUtils;

import java.math.BigDecimal;
import java.util.List;

public class BuyAndSellRates
{
    private final ExchangeRatesCalcBigDecimalAdapter buyRatesCalc;
    private final ExchangeRatesCalcBigDecimalAdapter sellRatesCalc;

    public BuyAndSellRates(
            List<Double> buyRates,
            List<Double> sellRates)
    {
        buyRatesCalc = new ExchangeRatesCalcBigDecimalAdapter(toArray(buyRates));
        sellRatesCalc = new ExchangeRatesCalcBigDecimalAdapter(toArray(sellRates));
    }

    private double[] toArray(List<Double> rates)
    {
        return ArrayUtils.toPrimitive(rates.toArray(new Double[rates.size()]));
    }

    public BigDecimal buyRatesMean()
    {
        return buyRatesCalc.mean();
    }

    public BigDecimal sellRatesStandardDeviation()
    {
        return sellRatesCalc.standardDeviation();
    }
}
