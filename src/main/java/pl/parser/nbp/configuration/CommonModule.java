package pl.parser.nbp.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import javax.inject.Singleton;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.time.Clock;

public abstract class CommonModule extends AbstractModule
{
    @Provides
    public Clock clock()
    {
        return Clock.systemUTC();
    }

    @Provides
    @Singleton
    public Client httpClient()
    {
        return ClientBuilder.newClient();
    }
}
