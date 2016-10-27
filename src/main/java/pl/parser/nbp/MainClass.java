package pl.parser.nbp;

import com.google.inject.Guice;
import com.google.inject.Injector;
import pl.parser.nbp.configuration.CommonModule;
import pl.parser.nbp.configuration.NbpApiModule;
import pl.parser.nbp.configuration.XmlFilesModule;
import pl.parser.nbp.currency.Currency;
import pl.parser.nbp.rates.BuyAndSellRates;
import pl.parser.nbp.rates.repository.ExchangeRateRepository;

import javax.ws.rs.client.Client;
import java.time.LocalDate;

/**
 * When executing with additional 4. argument 'useNbpApi' (for example EUR 2013-01-28 2013-01-31 useNbpApi)
 * new NBP REST API wil be used, but it is limited to date range of 367 days
 */
public class MainClass
{
    public static void main(String[] args)
    {

        Currency currency;
        LocalDate start;
        LocalDate end;
        try
        {
            checkNumberOfArguments(args);
            currency = Currency.valueOf(args[0]);
            start = LocalDate.parse(args[1]);
            end = LocalDate.parse(args[2]);
            if (start.compareTo(end) > 0)
            {
                System.out.println("End date is before start.");
                return;
            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Program executed with wrong arguments");
            System.out.println(e.getMessage());
            System.out.println("Example arguments: EUR 2013-01-28 2013-01-31");
            return;
        }

        Injector injector = Guice.createInjector(resolveModule(args));
        try
        {
            ExchangeRateRepository exchangeRateRepository = injector.getInstance(ExchangeRateRepository.class);
            BuyAndSellRates buyAndSellRates = exchangeRateRepository.loadForCurrencyAndPeriod(currency, start, end);

            System.out.println(buyAndSellRates.buyRatesMean());
            System.out.println(buyAndSellRates.sellRatesStandardDeviation());
        }
        finally
        {
            injector.getInstance(Client.class).close();
        }
    }

    private static CommonModule resolveModule(String[] args)
    {
        if (args.length > 3 && "useNbpApi".equals(args[3]))
        {
            return new NbpApiModule();
        }
        else
        {
            return new XmlFilesModule();
        }
    }

    private static void checkNumberOfArguments(String[] args)
    {
        if (args.length < 3)
        {
            throw new IllegalArgumentException("Expected 3 parameters");
        }
    }
}
