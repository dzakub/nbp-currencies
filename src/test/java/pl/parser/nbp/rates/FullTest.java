package pl.parser.nbp.rates;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.parser.nbp.currency.Currency;
import pl.parser.nbp.rates.repository.xmlFiles.XmlFileExchangeRateRepository;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

public class FullTest
{

    private XmlFileExchangeRateRepository repository;

    @BeforeMethod
    public void setUp() throws Exception
    {
        Clock clock = Clock.fixed(Instant.parse("2016-10-27T00:00:00.000Z"), ZoneId.of("UTC"));
        ClasspathResourceNbpFiles nbpFiles = new ClasspathResourceNbpFiles();
        repository = new XmlFileExchangeRateRepository(nbpFiles, clock);
    }

    @Test
    public void testLoadForCurrencyAndPeriod() throws Exception
    {
        BuyAndSellRates buyAndSellRates = repository.loadForCurrencyAndPeriod(Currency.USD, LocalDate.parse("2015-12-31"), LocalDate.parse("2016-01-04"));

        assertThat(buyAndSellRates.buyRatesMean()).isEqualTo("3.8552");
        assertThat(buyAndSellRates.sellRatesStandardDeviation()).isEqualTo("0.0071");
    }
}