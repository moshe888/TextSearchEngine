public class LRUCacheSearcher  {
    CacheSearcher C;
    int Max_value;

    public LRUCacheSearcher(CacheSearcher c, int max_value) {
        C = c;
        Max_value = max_value;
    }
}
