package test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LFUCacheSearcher implements CacheIOSearcher.CacheSearcher {
    private final CacheIOSearcher.CacheSearcher cacheSearcher;
    private final int limit;
    private final Map<IOSearcher.Result, Integer> usageCounter;

    public LFUCacheSearcher(CacheIOSearcher.CacheSearcher cacheSearcher, int limit) {
        this.cacheSearcher = cacheSearcher;
        this.limit = limit;
        this.usageCounter = new HashMap<>();
    }

    public IOSearcher.Result search(String text, String rootPath) throws FileNotFoundException {
        IOSearcher.Result result = cacheSearcher.search(text, rootPath);
        Integer usCounter = usageCounter.get(result);
        if (usCounter == null) {
            usageCounter.put(result, 0);
        } else {
            usageCounter.put(result, usCounter + 1);
        }
        if (getCachedResults().size() > limit) {
            removeLFU();
        }
        return result;
    }

    private void removeLFU() {
        IOSearcher.Result lfuResult = null;
        int minCount = Integer.MAX_VALUE;
        for (Map.Entry<IOSearcher.Result, Integer> entry : usageCounter.entrySet()) {
            if (entry.getValue() < minCount) {
                minCount = entry.getValue();
                lfuResult = entry.getKey();
            }
        }
        if(lfuResult == null) {
            // todo remove by LRU
        }
        remove(lfuResult);
    }

    @Override
    public Set<IOSearcher.Result> getCachedResults() {return  cacheSearcher.getCachedResults();}

    @Override
    public void clear() {
        cacheSearcher.clear();
        usageCounter.clear();
    }
    @Override
    public void remove(IOSearcher.Result result) {
        cacheSearcher.remove(result);
        usageCounter.remove(result);
    }
}

