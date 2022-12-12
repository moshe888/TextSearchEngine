import java.util.Observable;
import java.util.Observer;

public class ObservableCacheSearcher extends Observable {
    CacheSearcher c;

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }

    @Override
    public void notifyObservers(Object arg) {
        super.notifyObservers(arg);
    }

    public ObservableCacheSearcher(CacheSearcher c) {
        this.c = c;
    }
}
