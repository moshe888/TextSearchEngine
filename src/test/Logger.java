package test;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

public class Logger implements Observer {
    private String path;
    public Logger(String path,Observable o ) {
        this.path = path;
    }

    @Override
    public void update(Observable o, Object arg) {
//        File file = new File(path);


    }
}
