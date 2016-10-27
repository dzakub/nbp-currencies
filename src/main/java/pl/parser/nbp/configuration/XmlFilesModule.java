package pl.parser.nbp.configuration;

import pl.parser.nbp.rates.repository.ExchangeRateRepository;
import pl.parser.nbp.rates.repository.xmlFiles.NbpFiles;
import pl.parser.nbp.rates.repository.xmlFiles.NbpFilesHttpClient;
import pl.parser.nbp.rates.repository.xmlFiles.XmlFileExchangeRateRepository;

import javax.inject.Singleton;

public class XmlFilesModule extends CommonModule
{
    @Override
    protected void configure()
    {
        bind(NbpFiles.class).to(NbpFilesHttpClient.class).in(Singleton.class);
        bind(ExchangeRateRepository.class).to(XmlFileExchangeRateRepository.class).in(Singleton.class);
    }

}
