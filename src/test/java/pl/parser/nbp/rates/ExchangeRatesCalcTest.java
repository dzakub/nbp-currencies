package pl.parser.nbp.rates;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ExchangeRatesCalcTest
{

    @DataProvider
    public Object[][] dataForAverage()
    {
        return new Object[][]{
                {2., 2., 2., 2.},
                {6., 10., 2.},
                {3.8552, 3.8621, 3.8482}
        };
    }

    @Test(dataProvider = "dataForAverage")
    public void testAverage(
            double expectedMean,
            double... values) throws Exception
    {
        ExchangeRatesCalcBigDecimalAdapter rates = new ExchangeRatesCalcBigDecimalAdapter(values);

        assertThat(rates.mean()).isEqualTo(round(expectedMean));
    }

    @DataProvider
    public Object[][] dataForStandardDeviation()
    {
        return new Double[][]{
                {0., 2., 2., 2.},
                {4., 10., 2.},
                {10.8816, 18., 30., 21., 42., 55., 34., 45., 39., 38., 25.},
                {0.0071, 3.9401, 3.9260}
        };
    }

    @Test(dataProvider = "dataForStandardDeviation")
    public void testStandardDeviation(
            double expectedSD,
            double... values) throws Exception
    {
        ExchangeRatesCalcBigDecimalAdapter rates = new ExchangeRatesCalcBigDecimalAdapter(values);

        assertThat(rates.standardDeviation()).isEqualTo(round(expectedSD));
    }

    private BigDecimal round(double data)
    {
        return toStandardScaleAndRounding(data);
    }

    private BigDecimal toStandardScaleAndRounding(double val)
    {
        return RateStandardBigDecimal.valueOf(BigDecimal.valueOf(val));
    }

}