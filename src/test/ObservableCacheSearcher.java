package test;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

public class ObservableCacheSearcher extends Observable implements CacheSearcher {
    private final CacheSearcher cacheSearcher;
    private HashSet<Result> hashSet;

    public ObservableCacheSearcher(CacheSearcher cacheSearcher) {
        this.cacheSearcher = cacheSearcher;
        this.hashSet = new HashSet<>();
    }

    @Override
    public Result search(String text, String rootPath) throws FileNotFoundException {
        Result result = cacheSearcher.search(text, rootPath);
        if (!hashSet.contains(result)) {
            hashSet.add(result);
            setChanged();
            notifyObservers(result.getQuery() + " added");
        }
        return result;
    }

    @Override
    public Set<Result> getCachedResults() {
        return cacheSearcher.getCachedResults();
    }

    @Override
    public void clear() {
        cacheSearcher.clear();
    }

    @Override
    public void remove(Result result) {
        setChanged();
        notifyObservers(result.getQuery() + " removed");
        cacheSearcher.remove(result);
    }
}








//import java.io.FileNotFoundException;
//import java.util.Observable;
//import java.util.Observer;
//
//import java.util.Observable;
//
//public class ObservableCacheSearcher extends Observable   {
//    CacheSearcher cacheSearcher;
//
//
//    public ObservableCacheSearcher(CacheSearcher cacheSearcher) {
//        this.cacheSearcher = cacheSearcher;
//    }
//
//    public Result search(String query, String path) throws FileNotFoundException {
//        Result result = cacheSearcher.search(query, path);
//        setChanged();
//        String about = query + "added";
//        if(CacheIOSearcher.remove_ != null)
//            about += CacheIOSearcher.remove_ + "removed";
//
//        notifyObservers(about);
//        return result;
//    }
//}
//
