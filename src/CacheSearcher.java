import java.util.Set;

public interface CacheSearcher extends TextSearcher{
    public   Set<Result> getCachedResults();
    public void clear();


    public void remove(Result r);


}
