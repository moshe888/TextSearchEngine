package test;

import java.util.concurrent.*;

public class ParallelIOSearcher extends IOSearcher {
    public ParallelIOSearcher(IOSearcher io) {
        super();
    }

    public class ParallelIOSearchert extends ThreadPoolExecutor{

        public ParallelIOSearchert(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

     
    }


    ForkJoinPool Pool = new ForkJoinPool();
}

