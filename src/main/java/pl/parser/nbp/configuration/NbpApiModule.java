package pl.parser.nbp.configuration;

import pl.parser.nbp.rates.repository.ExchangeRateRepository;
import pl.parser.nbp.rates.repository.nbpApi.NbpApiRepository;

import javax.inject.Singleton;

public class NbpApiModule extends CommonModule
{
    @Override
    protected void configure()
    {
        bind(ExchangeRateRepository.class).to(NbpApiRepository.class).in(Singleton.class);
    }
}
