package pl.parser.nbp.rates.repository.xmlFiles.dto;

import com.google.common.base.MoreObjects;
import pl.parser.nbp.rates.repository.xmlFiles.xmlAdapters.LocalDateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name = "tabela_kursow")
public class ExchangeRateTable
{
    @XmlElement(name = "numer_tabeli")
    public String number;

    @XmlElement(name = "data_notowania")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate recordDate;

    @XmlElement(name = "data_publikacji")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate publicationDate;

    @XmlElement(name = "pozycja")
    public Position[] positions;

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this)
                .add("number", number)
                .add("recordDate", recordDate)
                .add("publicationDate", publicationDate)
                .add("positions", positions)
                .toString();
    }
}
