package test;

import java.io.FileNotFoundException;
import java.util.*;




public class CacheIOSearcher implements CacheSearcher {
    static   Map<SearchKey, Result> cache;
    static String remove_ = new String();



        private  TextSearcher searcher;

        public CacheIOSearcher(TextSearcher searcher) {
             this.searcher = searcher;
            cache = new HashMap<>();

        }


        @Override
        public Result search(String text, String rootPath) throws FileNotFoundException {
            SearchKey key = new SearchKey(text, rootPath);
            Result result = cache.get(key);
            if (result == null) {
                System.out.println("result null");
                 result = searcher.search(text, rootPath);
                cache.put(key, result);
            }
            else {
                System.out.println(result.getQuery()+" - "+result.getAnswer());

            }
            return result;
        }

        @Override
        public Set<Result> getCachedResults() {
            return new HashSet<>(cache.values());
        }

        @Override
        public void clear() {
            remove_ = null;
            cache.clear();
        }


    @Override
        public void remove(Result result) {
            remove_ = result.getQuery();
            cache.values().remove(result);
        }
    }

interface CacheSearcher extends TextSearcher {
    public Set<Result> getCachedResults();
    public void clear();
    public void remove(Result result);
}

class SearchKey {
    private final String text;
    private final String rootPath;

    public SearchKey(String text, String rootPath) {
        this.text = text;
        this.rootPath = rootPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchKey that = (SearchKey) o;
        return Objects.equals(text, that.text) &&
                Objects.equals(rootPath, that.rootPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, rootPath);
    }
}