package pl.parser.nbp.rates.repository;

import pl.parser.nbp.currency.Currency;
import pl.parser.nbp.rates.BuyAndSellRates;

import java.time.LocalDate;

public interface ExchangeRateRepository
{
    BuyAndSellRates loadForCurrencyAndPeriod(
            Currency currency,
            LocalDate start,
            LocalDate end);
}
