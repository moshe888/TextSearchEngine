package test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class Logger implements Observer {
    private static Logger instance;
    private String fileName;
    private FileWriter fileWriter;

    private Logger(String fileName, ObservableCacheSearcher cacheSearcher) {
        this.fileName = fileName;
        cacheSearcher.addObserver(this);
        try {
            fileWriter = new FileWriter(fileName, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getInstance(String fileName, ObservableCacheSearcher cacheSearcher) {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger(fileName, cacheSearcher);
                }
            }
        }
        return instance;
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            fileWriter.write((String) arg + " " + CacheIOSearcher.cache.size() + "\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

