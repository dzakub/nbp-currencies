package pl.parser.nbp.rates;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class RateStandardBigDecimal
{

    public static final int SCALE = 4;
    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    private RateStandardBigDecimal()
    {
    }

    public static BigDecimal valueOf(BigDecimal value)
    {
        return value.setScale(SCALE, ROUNDING_MODE);
    }

    public static BigDecimal valueOf(double value)
    {
        if (Double.isNaN(value))
        {
            return BigDecimal.ZERO;
        }
        return valueOf(BigDecimal.valueOf(value));
    }

}
