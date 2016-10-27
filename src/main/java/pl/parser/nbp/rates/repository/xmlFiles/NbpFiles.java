package pl.parser.nbp.rates.repository.xmlFiles;

public interface NbpFiles
{
    <T> T getFileContent(
            String fileName,
            Class<T> responseType);
}
