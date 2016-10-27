package pl.parser.nbp.rates;

import java.math.BigDecimal;

public class ExchangeRatesCalcBigDecimalAdapter
{
    private final ExchangeRatesCalc exchangeRatesCalc;

    public ExchangeRatesCalcBigDecimalAdapter(double[] rates)
    {
        exchangeRatesCalc = new ExchangeRatesCalc(rates);
    }

    public BigDecimal mean()
    {
        double mean = exchangeRatesCalc.mean();
        return RateStandardBigDecimal.valueOf(mean);
    }

    public BigDecimal standardDeviation()
    {
        double standardDeviation = exchangeRatesCalc.standardDeviation();
        return RateStandardBigDecimal.valueOf(standardDeviation);
    }
}
