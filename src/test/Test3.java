package  test;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;

public class Test3 {


    public static void main(String[] args) throws FileNotFoundException, IOException {
        String path = "C:\\Users\\Moshe Sayada\\IdeaProjects\\TextSearchEngine\\search_folder";
        String query = "a";

        // Test IOSearcher
        System.out.println("Test IOSearcher:");
        IOSearcher ios = new IOSearcher();
        System.out.println(Thread.activeCount());
        System.out.println(ios.search(query, path));

        // Test ParallelIOSearcher
        System.out.println("\nTest ParallelIOSearcher:");

        ParallelIOSearcher ps = new ParallelIOSearcher();
        System.out.println(ps.search(query, path));
        System.out.println(Thread.activeCount());

        // Test CacheIOSearcher
        System.out.println("\nTest CacheIOSearcher:");
        IOSearcher ios3 = new IOSearcher();
        CacheIOSearcher cs = new CacheIOSearcher(ios3);
        System.out.println(cs.search(query, path));
        System.out.println(cs.getCachedResults().size());
        System.out.println(cs.search(query, path));
        System.out.println(cs.getCachedResults().size());
        System.out.println(cs.search("fish", path));
        System.out.println(cs.getCachedResults().size());



        // Test LRUCacheSearcher
        System.out.println("\nTest LRUCacheSearcher:");
        IOSearcher i1 = new IOSearcher();
        CacheIOSearcher ios4 = new CacheIOSearcher(i1);
         LRUCacheSearcher lru = new LRUCacheSearcher(ios4, 2);
        System.out.println(lru.search(query, path));
        System.out.println(lru.search("java", path));
        System.out.println(lru.search("java", path));
        System.out.println(lru.search(query, path));

        // Test LFUCacheSearcher
        System.out.println("\nTest LFUCacheSearcher:");
        IOSearcher i2 = new IOSearcher();
        CacheIOSearcher ios5 = new CacheIOSearcher(i2);
        LFUCacheSearcher lfu = new LFUCacheSearcher(ios5, 2);
        System.out.println(lfu.search(query, path));
        System.out.println(lfu.search("java", path));
        System.out.println(lfu.search("java", path));
        System.out.println(lfu.search(query, path));

        // Test ObservableCacheSearcher
        System.out.println("\nTest ObservableCacheSearcher:");
        IOSearcher i3 = new IOSearcher();
        CacheIOSearcher ios6 = new CacheIOSearcher(i3);
        ObservableCacheSearcher ocs = new ObservableCacheSearcher(ios6);
        ocs.addObserver(new Logger("log.txt", ocs));
        System.out.println(ocs.search(query, path));
        System.out.println(ocs.search(query, path));
        ocs.remove(ocs.getCachedResults().iterator().next());




        CacheIOSearcher cs1 = new CacheIOSearcher(new IOSearcher());
        Class<?> csClass = cs1.getClass();
        System.out.println("CacheIOSearcher class: " + csClass.getName());
        System.out.println("Superclass: " + csClass.getSuperclass().getName());

        Class<?>[] interfaces = csClass.getInterfaces();
        System.out.println("Interfaces implemented by CacheIOSearcher:");
        for (Class<?> intf : interfaces) {
            System.out.println(intf.getName());
        }

        Field[] fields = csClass.getDeclaredFields();
        System.out.println("Fields of CacheIOSearcher:");
        for (Field field : fields) {
            System.out.println(field.getName() + " (" + field.getType().getName() + ")");
        }


    }


}
