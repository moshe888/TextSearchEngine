import java.io.FileNotFoundException;
import java.util.Set;

public class LRUCacheSearcher implements  CacheSearcher  {
    CacheSearcher C;
    int Max_value;

    public LRUCacheSearcher(CacheSearcher c, int max_value) {
        C = c;
        Max_value = max_value;
    }

    @Override
    public Set<Result> getCachedResults() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public void remove(Result r) {

    }

    @Override
    public Result search(String text, String rootPath) throws FileNotFoundException {
        return null;
    }

 }
