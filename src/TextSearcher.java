import java.io.FileNotFoundException;

public interface TextSearcher {
    Result search(String text, String rootPath) throws FileNotFoundException;
}