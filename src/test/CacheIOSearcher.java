package test;

import java.io.FileNotFoundException;
import java.util.*;




public class CacheIOSearcher  {
    static   Map<SearchKey, IOSearcher.Result> cache;
    static String remove_ = new String();


    interface CacheSearcher extends IOSearcher.TextSearcher {
        public Set<IOSearcher.Result> getCachedResults();
        public void clear();
        public void remove(IOSearcher.Result result);
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
    class CacheIOSearcherImpl implements CacheSearcher {



        private final IOSearcher.TextSearcher searcher;

        public CacheIOSearcherImpl(IOSearcher.TextSearcher searcher) {
             this.searcher = searcher;
            cache = new HashMap<>();

        }


        @Override
        public IOSearcher.Result search(String text, String rootPath) throws FileNotFoundException {
            SearchKey key = new SearchKey(text, rootPath);
            IOSearcher.Result result = cache.get(key);
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
        public Set<IOSearcher.Result> getCachedResults() {
            return new HashSet<>(cache.values());
        }

        @Override
        public void clear() {
            remove_ = null;
            cache.clear();
        }

        @Override
        public void remove(IOSearcher.Result result) {
            remove_ = result.getQuery();
            cache.values().remove(result);
        }
    }
}