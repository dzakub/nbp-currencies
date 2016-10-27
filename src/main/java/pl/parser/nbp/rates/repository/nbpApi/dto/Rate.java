package pl.parser.nbp.rates.repository.nbpApi.dto;

import com.google.common.base.MoreObjects;

public class Rate
{
    public String no;
    public String effectiveDate;
    public Double bid;
    public Double ask;

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this)
                .add("no", no)
                .add("effectiveDate", effectiveDate)
                .add("bid", bid)
                .add("ask", ask)
                .toString();
    }
}
