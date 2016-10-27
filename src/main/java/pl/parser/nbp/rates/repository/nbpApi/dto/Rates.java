package pl.parser.nbp.rates.repository.nbpApi.dto;

import com.google.common.base.MoreObjects;
import pl.parser.nbp.currency.Currency;

public class Rates
{
    public String table;
    public String currency;
    public Currency code;
    public Rate[] rates;

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this)
                .add("table", table)
                .add("currency", currency)
                .add("code", code)
                .add("rates", rates)
                .toString();
    }
}
