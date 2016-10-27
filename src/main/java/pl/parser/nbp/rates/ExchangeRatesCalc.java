package pl.parser.nbp.rates;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import java.util.Arrays;

public class ExchangeRatesCalc
{
    private final double[] rates;
    private final Mean mean = new Mean();
    private final StandardDeviation standardDeviation = new StandardDeviation(false);

    public ExchangeRatesCalc(double[] rates)
    {
        this.rates = Arrays.copyOf(rates, rates.length);
    }

    public double mean()
    {
        return mean.evaluate(rates, 0, rates.length);
    }

    public double standardDeviation()
    {
        return standardDeviation.evaluate(rates);
    }
}
