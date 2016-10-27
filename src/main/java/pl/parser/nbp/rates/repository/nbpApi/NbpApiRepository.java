package pl.parser.nbp.rates.repository.nbpApi;

import pl.parser.nbp.currency.Currency;
import pl.parser.nbp.rates.BuyAndSellRates;
import pl.parser.nbp.rates.repository.ExchangeRateRepository;
import pl.parser.nbp.rates.repository.nbpApi.dto.Rates;
import pl.parser.nbp.util.HttpJerseyUtil;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NbpApiRepository implements ExchangeRateRepository
{
    private final WebTarget webTarget;

    @Inject
    public NbpApiRepository(Client client)
    {
        webTarget = client.target("http://api.nbp.pl/api/exchangerates/rates/c");
    }

    @Override
    public BuyAndSellRates loadForCurrencyAndPeriod(
            Currency currency,
            LocalDate start,
            LocalDate end)
    {
        WebTarget resourceWebTarget = webTarget
                .path(currency.name().toLowerCase())
                .path(start.toString())
                .path(end.toString())
                .queryParam("format", "json");

        Invocation.Builder invocationBuilder = resourceWebTarget.request();
        Response response = invocationBuilder.get();

        Rates rates = HttpJerseyUtil.handleErrorResponse(Rates.class, response);
        return new BuyAndSellRates(collectBuyRates(rates), collectSellRates(rates));

    }

    private List<Double> collectSellRates(Rates rates)
    {
        return Arrays.stream(rates.rates).map((rate -> rate.ask)).collect(Collectors.toList());
    }

    private List<Double> collectBuyRates(Rates rates)
    {
        return Arrays.stream(rates.rates).map((rate -> rate.bid)).collect(Collectors.toList());
    }
}
