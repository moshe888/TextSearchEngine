public class LFUCacheSearcher {
    CacheSearcher C;
    int Max_value;

    public LFUCacheSearcher(CacheSearcher c, int max_value) {
        C = c;
        Max_value = max_value;
    }
}
