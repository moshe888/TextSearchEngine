package test;

import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

import java.util.Observable;

public class ObservableCacheSearcher extends Observable {
    CacheSearcher cacheSearcher;

    public ObservableCacheSearcher(CacheSearcher cacheSearcher) {
        this.cacheSearcher = cacheSearcher;
    }

    public Result search(String query, String path) throws FileNotFoundException {
        Result result = cacheSearcher.search(query, path);
        setChanged();
        String about = query + "added";
        if(CacheIOSearcher.remove_ != null)
            about += CacheIOSearcher.remove_ + "removed";
        notifyObservers(about);
        return result;
    }
}

