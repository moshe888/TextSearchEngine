package test;

import java.io.FileNotFoundException;
import java.util.*;

public class LRUCacheSearcher implements CacheSearcher {
    private final CacheSearcher cacheSearcher;
    private Deque<Result> doublyQueue;

    private HashSet<Result> hashSet;

    // maximum capacity of cache
    private final int CACHE_SIZE;

    public LRUCacheSearcher(CacheSearcher cacheSearcher, int capacity) {
        this.cacheSearcher = cacheSearcher;
        doublyQueue = new LinkedList<>();
        hashSet = new HashSet<>();
        CACHE_SIZE = capacity;
    }

    @Override
    public Result search(String text, String rootPath) throws FileNotFoundException {
        Result result = cacheSearcher.search(text, rootPath);
        refer(result);
        return result;
    }

    /* Refer the result within the LRU cache */
    public void refer(Result result) {
        if (!hashSet.contains(result)) {
            if (getCachedResults().size() == CACHE_SIZE) {
                System.out.println("remove from LRU cache");
                Result lastResult = doublyQueue.removeLast();
                remove(lastResult);
            }
        } else {
            doublyQueue.remove(result);
        }
        doublyQueue.push(result);
        hashSet.add(result);
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
        hashSet.remove(result);
        cacheSearcher.remove(result);
    }
}
