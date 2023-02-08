package test;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {


    public static void main(String[] args) throws FileNotFoundException {
        IOSearcher searcher = new IOSearcher();
//        IOSearcher.Result result = searcher.search("dog", "C:\\Users\\Moshe Sayada\\IdeaProjects\\TextSearchEngine\\data");
//        System.out.println("Result: " + result.getQuery() + result.getAnswer());

        CacheIOSearcher cacheSearcher = new CacheIOSearcher( searcher);
         while (true) {
             Scanner scan = new Scanner(System.in);
             System.out.println("enter path:");
             String path = scan.nextLine();
             System.out.println("enter text search:");
             String text = scan.nextLine();

             Result r = cacheSearcher.search(text, path);
             System.out.println(r.getAnswer().get("a1.txt"));
          }

    }
}
//import org.junit.Before;
//        import org.junit.Test;
//
//        import java.io.FileNotFoundException;
//        import java.util.HashMap;
//        import java.util.Map;
//        import java.util.Set;
//
//        import static org.junit.Assert.assertEquals;
//        import static org.junit.Assert.assertTrue;
//        import static org.mockito.Mockito.*;
//
//public class CacheIOSearcherTest {
//    private IOSearcher.TextSearcher textSearcher;
//    private CacheIOSearcher.ccc cacheIOSearcher;
//    private Map<CacheIOSearcher.SearchKey, IOSearcher.Result> cache;
//
//    @Before
//    public void setUp() {
//        textSearcher = mock(IOSearcher.TextSearcher.class);
//        cache = new HashMap<>();
//        cacheIOSearcher = new CacheIOSearcher.ccc(cache, textSearcher);
//    }
//
//    @Test
//    public void testSearchCachedResult() throws FileNotFoundException {
//        String text = "text";
//        String rootPath = "rootPath";
//        IOSearcher.Result result = new IOSearcher.Result(text, rootPath);
//        when(textSearcher.search(text, rootPath)).thenReturn(result);
//
//        IOSearcher.Result searchedResult = cacheIOSearcher.search(text, rootPath);
//        assertEquals(result, searchedResult);
//        verify(textSearcher, times(1)).search(text, rootPath);
//
//        // Searching again should return the same result from cache
//        searchedResult = cacheIOSearcher.search(text, rootPath);
//        assertEquals(result, searchedResult);
//        verify(textSearcher, times(1)).search(text, rootPath);
//    }
//
//    @Test
//    public void testGetCachedResults() throws FileNotFoundException {
//        String text = "text";
//        String rootPath = "rootPath";
//        IOSearcher.Result result = new IOSearcher.Result(text, rootPath);
//        when(textSearcher.search(text, rootPath)).thenReturn(result);
//
//        cacheIOSearcher.search(text, rootPath);
//        Set<IOSearcher.Result> cachedResults = cacheIOSearcher.getCachedResults();
//        assertEquals(1, cachedResults.size());
//        assertTrue(cachedResults.contains(result));
//    }
//
//    @Test
//    public void testClear() throws FileNotFoundException {
//        String text = "text";
//        String rootPath = "rootPath";
//        IOSearcher.Result result = new IOSearcher.Result(text, rootPath);
//        when(textSearcher.search(text, rootPath)).thenReturn(result);
//
//        cacheIOSearcher.search(text, rootPath);
//        cacheIOSearcher.clear();
//        Set<IOSearcher.Result> cachedResults = cache




