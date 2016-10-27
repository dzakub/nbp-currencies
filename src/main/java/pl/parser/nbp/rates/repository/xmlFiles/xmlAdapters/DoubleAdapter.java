package pl.parser.nbp.rates.repository.xmlFiles.xmlAdapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DoubleAdapter extends XmlAdapter<String, Double>
{
    @Override
    public Double unmarshal(String number) throws Exception
    {
        return Double.valueOf(number.replace(',', '.'));
    }

    @Override
    public String marshal(Double number) throws Exception
    {
        return number.toString().replace('.', ',');
    }
}
