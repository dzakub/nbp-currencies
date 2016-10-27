package pl.parser.nbp.rates;

import pl.parser.nbp.rates.repository.xmlFiles.NbpFiles;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class ClasspathResourceNbpFiles implements NbpFiles
{
    @Override
    public <T> T getFileContent(
            String fileName,
            Class<T> responseType)
    {
        try (InputStream resourceAsStream = ClasspathResourceNbpFiles.class.getResourceAsStream(fileName))
        {
            if (String.class.equals(responseType))
            {
                return (T) readTextFile(resourceAsStream);
            }

            return readXmlFile(responseType, resourceAsStream);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    private <T> T readXmlFile(
            Class<T> responseType,
            InputStream resourceAsStream)
    {
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(responseType);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (T) unmarshaller.unmarshal(resourceAsStream);
        }
        catch (JAXBException e)
        {
            throw new RuntimeException(e);
        }
    }

    private String readTextFile(InputStream resourceAsStream)
    {
        try (InputStreamReader reader = new InputStreamReader(resourceAsStream);
             BufferedReader br = new BufferedReader(reader))
        {
            return br.lines().collect(Collectors.joining("\r\n"));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
