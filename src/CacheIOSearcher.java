import java.io.FileNotFoundException;
import java.util.*;

public class CacheIOSearcher  implements CacheSearcher{

    Map<String, Result> cache = new HashMap<>() ;
    IOSearcher io ;
    private final Object writeLock = new Object();

    public CacheIOSearcher( IOSearcher io) {
        this.io = new IOSearcher();
     }



    @Override
    public Set<Result> getCachedResults() {
        Set<Result> results = new HashSet<>();
        for(String s : cache.keySet())//??
        {
            results.add(cache.get(s));
        }

        return results;
    }

    @Override
    public void clear() {
        cache.clear();
     }

    @Override
    public void remove(Result r) {
        for(String s : cache.keySet())//??
        {
            if(cache.get(s).equals(r))
            {
                cache.remove(s);
            }
        }

    }

    @Override
    public Result search(String text, String rootPath) throws FileNotFoundException {
        String search_ = text+","+rootPath;//?

            if (cache.get(search_) == null) {
                Result r = io.search(text, rootPath);
                synchronized (writeLock) {
                    cache.put(search_, r);
                    System.out.println("added result");

                }
            }
                else{
                    System.out.println("no added result");
                }


        return cache.get(search_);

    }
}
