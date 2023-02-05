package test;

import java.io.FileNotFoundException;
import java.util.*;

public class LRUCacheSearcher implements CacheIOSearcher.CacheSearcher {
    private final CacheIOSearcher.CacheSearcher cacheSearcher;
    private Deque<IOSearcher.Result> doublyQueue;

    private HashSet<IOSearcher.Result> hashSet;

    // maximum capacity of cache
    private final int CACHE_SIZE;

    public LRUCacheSearcher(CacheIOSearcher.CacheSearcher cacheSearcher, int capacity) {
        this.cacheSearcher = cacheSearcher;
        doublyQueue = new LinkedList<>();
        hashSet = new HashSet<>();
        CACHE_SIZE = capacity;
    }

    @Override
    public IOSearcher.Result search(String text, String rootPath) throws FileNotFoundException {
        IOSearcher.Result result = cacheSearcher.search(text, rootPath);
        refer(result);
        return result;
    }

    /* Refer the result within the LRU cache */
    public void refer(IOSearcher.Result result) {
        if (!hashSet.contains(result)) {
            if (getCachedResults().size() == CACHE_SIZE) {
                System.out.println("remove from LRU cache");
                IOSearcher.Result lastResult = doublyQueue.removeLast();
                remove(lastResult);
            }
        } else {
            doublyQueue.remove(result);
        }
        doublyQueue.push(result);
        hashSet.add(result);
    }

    @Override
    public Set<IOSearcher.Result> getCachedResults() {
        return cacheSearcher.getCachedResults();
    }

    @Override
    public void clear() {
        cacheSearcher.clear();
    }

    @Override
    public void remove(IOSearcher.Result result) {
        hashSet.remove(result);
        cacheSearcher.remove(result);
    }
}
