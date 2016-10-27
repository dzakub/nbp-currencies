package pl.parser.nbp.rates.repository.xmlFiles;

import pl.parser.nbp.currency.Currency;
import pl.parser.nbp.rates.BuyAndSellRates;
import pl.parser.nbp.rates.repository.ExchangeRateRepository;
import pl.parser.nbp.rates.repository.xmlFiles.dto.ExchangeRateTable;
import pl.parser.nbp.rates.repository.xmlFiles.dto.Position;

import javax.inject.Inject;
import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class XmlFileExchangeRateRepository implements ExchangeRateRepository
{
    private final NbpFiles nbpFiles;
    private final Clock clock;

    @Inject
    public XmlFileExchangeRateRepository(
            NbpFiles nbpFiles,
            Clock clock)
    {
        this.nbpFiles = nbpFiles;
        this.clock = clock;
    }

    @Override
    public BuyAndSellRates loadForCurrencyAndPeriod(
            Currency currency,
            LocalDate start,
            LocalDate end)
    {

        List<String> dirFileNamesByYear = resolveAllDirFileNames(start, end);
        List<String> dirFilesByYear = selectCFileNamesOnly(dirFileNamesByYear);

        List<String> rateFilesToDownload = resolveCFileNamesForPeriod(dirFilesByYear, start, end);

        List<Double> buyRates = new LinkedList<>();
        List<Double> sellRates = new LinkedList<>();
        for (String file : rateFilesToDownload)
        {
            ExchangeRateTable exchangeRateTable = nbpFiles.getFileContent(file + ".xml", ExchangeRateTable.class);
            Optional<Position> foundPosition = Arrays.stream(exchangeRateTable.positions)
                    .filter((p -> p.currency == currency))
                    .findFirst();
            if (foundPosition.isPresent())
            {
                buyRates.add(foundPosition.get().buyRate);
                sellRates.add(foundPosition.get().sellRate);
            }
        }
        return new BuyAndSellRates(buyRates, sellRates);
    }

    private List<String> selectCFileNamesOnly(List<String> dirFileNames)
    {
        List<String> result = new ArrayList<>(dirFileNames.size() * 260);
        for (String dirFileName : dirFileNames)
        {
            String content = nbpFiles.getFileContent(dirFileName, String.class);

            String[] split = content.substring(content.indexOf("c")).split("\r\n");
            List<String> cFileNames = Arrays.stream(split)
                    .filter((s -> s.startsWith("c")))
                    .collect(Collectors.toList());
            result.addAll(cFileNames);
        }
        return result;
    }

    private List<String> resolveCFileNamesForPeriod(
            List<String> allCFileNames,
            LocalDate start,
            LocalDate end)
    {
        Comparator<String> xmlFileNamesComparator = (String s1, String s2) -> s1.substring(5).compareTo(s2.substring(5));
        Collections.sort(allCFileNames, xmlFileNamesComparator);

        List<String> result = new LinkedList<>();
        for (LocalDate date = start; date.compareTo(end) <= 0; date = date.plusDays(1))
        {
            String dateToFind = date.format(DateTimeFormatter.ofPattern("uuMMdd"));
            int index = Collections.binarySearch(allCFileNames, dateToFind, (String s1, String s2) -> s1.substring(5).compareTo(s2));
            if (index > -1 && index < allCFileNames.size() && allCFileNames.get(index).endsWith(dateToFind))
            {
                result.add(allCFileNames.get(index));
            }
        }
        return result;
    }

    private List<String> resolveAllDirFileNames(
            LocalDate start,
            LocalDate end)
    {
        int currentYear = LocalDate.now(clock).getYear();
        int startYear = start.getYear();
        int endYear = end.getYear();

        List<String> result = new LinkedList<>();
        for (int i = startYear; i <= endYear; i++)
        {
            if (currentYear == i)
            {
                result.add("dir.txt");
            }
            else
            {
                result.add("dir" + i + ".txt");
            }
        }
        return result;
    }
}
