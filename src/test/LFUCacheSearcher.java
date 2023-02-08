package test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LFUCacheSearcher implements CacheSearcher {
    private final CacheSearcher cacheSearcher;
    private final int limit;
    private final Map<Result, Integer> usageCounter;

    public LFUCacheSearcher(CacheSearcher cacheSearcher, int limit) {
        this.cacheSearcher = cacheSearcher;
        this.limit = limit;
        this.usageCounter = new HashMap<>();
    }

    public Result search(String text, String rootPath) throws FileNotFoundException {
        Result result = cacheSearcher.search(text, rootPath);
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
        Result lfuResult = null;
        int minCount = Integer.MAX_VALUE;
        for (Map.Entry<Result, Integer> entry : usageCounter.entrySet()) {
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
    public Set<Result> getCachedResults() {return  cacheSearcher.getCachedResults();}

    @Override
    public void clear() {
        cacheSearcher.clear();
        usageCounter.clear();
    }
    @Override
    public void remove(Result result) {
        cacheSearcher.remove(result);
        usageCounter.remove(result);
    }
}

