package pl.parser.nbp.util;

import javax.ws.rs.core.Response;

public final class HttpJerseyUtil
{
    private HttpJerseyUtil()
    {
    }

    public static <T> T handleErrorResponse(
            Class<T> responseType,
            Response response)
    {

        try
        {
            if (response.getStatusInfo().equals(Response.Status.OK))
            {
                return response.readEntity(responseType);
            }
            else
            {
                throw new RuntimeException("Error while connecting to NBP, HTTP status: " + response.getStatusInfo());
            }
        }
        finally
        {
            response.close();
        }
    }
}
