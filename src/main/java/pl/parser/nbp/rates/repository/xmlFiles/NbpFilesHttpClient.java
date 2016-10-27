package pl.parser.nbp.rates.repository.xmlFiles;

import pl.parser.nbp.util.HttpJerseyUtil;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class NbpFilesHttpClient implements NbpFiles
{
    private final WebTarget webTarget;

    @Inject
    public NbpFilesHttpClient(Client client)
    {
        webTarget = client.target("http://www.nbp.pl/kursy/xml");
    }

    @Override
    public <T> T getFileContent(
            String fileName,
            Class<T> responseType)
    {
        WebTarget resourceWebTarget = webTarget.path(fileName);
        Invocation.Builder invocationBuilder = resourceWebTarget.request();
        Response response = invocationBuilder.get();

        return HttpJerseyUtil.handleErrorResponse(responseType, response);
    }
}
