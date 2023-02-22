package test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class Logger implements Observer {
    private static Logger instance;
    private String filename;
    private CacheSearcher cacheSearcher;

    public Logger(String filename, CacheSearcher cacheSearcher) {
        this.filename = filename;
        this.cacheSearcher = cacheSearcher;
    }

    public static synchronized Logger getInstance(String filename, CacheSearcher cacheSearcher) {
        if (instance == null) {
            instance = new Logger(filename, cacheSearcher);
        }
        return instance;
    }

    @Override
    public void update(Observable o, Object arg) {
        String logText = (String) arg;
        int cachesCount = cacheSearcher.getCachedResults().size();
        try {
            FileWriter writer = new FileWriter(new File(filename), true);
            writer.write(String.format("%s %d\n", logText, cachesCount));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}