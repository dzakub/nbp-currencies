package pl.parser.nbp.rates.repository.xmlFiles.dto;

import com.google.common.base.MoreObjects;
import pl.parser.nbp.currency.Currency;
import pl.parser.nbp.rates.repository.xmlFiles.xmlAdapters.DoubleAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class Position
{
    @XmlElement(name = "nazwa_waluty")
    public String currencyFullName;

    @XmlElement(name = "przelicznik")
    @XmlJavaTypeAdapter(DoubleAdapter.class)
    public Double multiplier;

    @XmlElement(name = "kod_waluty")
    public Currency currency;

    @XmlElement(name = "kurs_kupna")
    @XmlJavaTypeAdapter(DoubleAdapter.class)
    public Double buyRate;

    @XmlJavaTypeAdapter(DoubleAdapter.class)
    @XmlElement(name = "kurs_sprzedazy")
    public Double sellRate;

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this)
                .add("currencyFullName", currencyFullName)
                .add("multiplier", multiplier)
                .add("currency", currency)
                .add("buyRate", buyRate)
                .add("sellRate", sellRate)
                .toString();
    }
}
