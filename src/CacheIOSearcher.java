import java.io.FileNotFoundException;
import java.util.Queue;
import java.util.Set;

public class CacheIOSearcher  implements CacheSearcher{
//    public interface CacheSearcher extends TextSearcher{
//        public   Set<Result> getCachedResults();
//        public void clear();
//
//
//        public void remove(Result r);
//
//
//    }
    Set<Result> q;
    IOSearcher io ;

    public CacheIOSearcher(Set<Result> q, IOSearcher io) {
        this.q = q;
        this.io = new IOSearcher();
    }



    @Override
    public Set<Result> getCachedResults() {

        return q;
    }

    @Override
    public void clear() {

    }

    @Override
    public void remove(Result r) {

    }

    @Override
    public Result search(String text, String rootPath) throws FileNotFoundException {
        return null;
    }
}
