package test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Test {
    public static void main(String[] args) throws Exception {
        org.junit.runner.JUnitCore.main(
                "test.IOSearcherTest",
                "test.ParallelIOSearcherTest",
                "test.CacheIOSearcherTest",
                "test.LRUCacheSearcherTest",
                "test.LFUCacheSearcherTest",
                "test.ObservableCacheSearcherTest"
        );
    }

    private final String path = "C:\\Users\\Moshe Sayada\\IdeaProjects\\TextSearchEngine\\data";
    private final String query = "dog";

    @Test3
    void testIOSearcher() throws IOException {
        IOSearcher searcher = new IOSearcher();
        Result result = searcher.search(query, path);
        assertNotNull(result);
        assertTrue(result.getQuery().contains(query));
    }

    @Test3
    void testParallelIOSearcher() throws IOException {
        ParallelIOSearcher searcher = new ParallelIOSearcher();
        Result result = searcher.search(query, path);
        assertNotNull(result);
        assertTrue(result.getQuery().contains("dog"));
    }

    @Test3
    void testCacheIOSearcher() throws IOException {
        IOSearcher ios = new IOSearcher();
        CacheIOSearcher searcher = new CacheIOSearcher(ios);
        Result result = searcher.search(query, path);
        assertNotNull(result);
        assertTrue(result.getQuery().contains("dog"));
        assertEquals(result, searcher.search(query, path));
        CacheIOSearcher cs = new CacheIOSearcher(ios);
        cs.search(query, path);
        int size = cs.getCachedResults().size();
        cs.search(query, path);
        //The cache does not increase
        assertTrue(cs.getCachedResults().size() == size);
        cs.getCachedResults().size();
        cs.search("fish", path);
        assertTrue(cs.getCachedResults().size()==size+1 );

    }

    @Test3
    void testLRUCacheSearcher() throws IOException {
        IOSearcher ios = new IOSearcher();
        CacheIOSearcher cs = new CacheIOSearcher(ios);
        LRUCacheSearcher searcher = new LRUCacheSearcher(cs, 2);
        Result result1 = searcher.search(query, path);
        assertNotNull(result1);
        assertTrue(result1.getQuery().contains(query));
        Result result2 = searcher.search("kkkkkkkkkkkkkkkk", path);
        assertNotNull(result2);
        assertTrue(result2.getAnswer().isEmpty() );
         Result result3 = searcher.search(query, path);
        assertNotNull(result3);
        assertTrue(result3.getQuery().contains(query));
//        assertNull(searcher.getCachedResults().contains("java"));
    }

    @Test3
    void testLFUCacheSearcher() throws IOException {
        IOSearcher ios = new IOSearcher();
        CacheIOSearcher cs = new CacheIOSearcher(ios);
        LFUCacheSearcher searcher = new LFUCacheSearcher(cs, 2);
        Result result1 = searcher.search(query, path);
        assertNotNull(result1);
        assertTrue(result1.getQuery().contains(query));

//        assertNull(searcher.getCache().get("java"));
    }

    @Test3
    void testObservableCacheSearcher() throws IOException {
        IOSearcher ios = new IOSearcher();
        CacheIOSearcher cs = new CacheIOSearcher(ios);
        ObservableCacheSearcher searcher = new ObservableCacheSearcher(cs);
        Logger logger = new Logger("log.txt", searcher);
        searcher.addObserver(logger);
        Result result1 = searcher.search(query, path);
        assertNotNull(result1);
        assertTrue(result1.getQuery().contains(query));

        searcher.remove(searcher.getCachedResults().iterator().next());
    }

    @Test3
    void testLogger() throws IOException {
        IOSearcher ios = new IOSearcher();
        CacheIOSearcher cs = new CacheIOSearcher(ios);
        ObservableCacheSearcher ocs = new ObservableCacheSearcher(cs);
        Logger logger = new Logger("log.txt", cs);
        ocs.addObserver(logger);
        Result result1 = ocs.search(query, path);
        assertNotNull(result1);
        assertTrue(result1.getQuery().contains(query));
//        assertEquals(logger.getLog(), "Search completed. Cache size: 1\n");
    }


}
