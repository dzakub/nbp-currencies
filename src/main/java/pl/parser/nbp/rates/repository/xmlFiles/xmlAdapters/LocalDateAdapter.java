package pl.parser.nbp.rates.repository.xmlFiles.xmlAdapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class LocalDateAdapter extends XmlAdapter<String, java.time.LocalDate>
{
    @Override
    public LocalDate unmarshal(String date) throws Exception
    {
        return LocalDate.parse(date);
    }

    @Override
    public String marshal(LocalDate date) throws Exception
    {
        return date.toString();
    }
}
