package test;

import java.io.FileNotFoundException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIOSearcher implements TextSearcher {

    public final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

    @Override
    public Result search(String text, String rootPath) {
        SearchTask task = new SearchTask(text, rootPath);
        return FORK_JOIN_POOL.invoke(task);
    }

    private static class SearchTask extends RecursiveTask<Result> {
        private String text;
        private String rootPath;

        public SearchTask(String text, String rootPath) {
            this.text = text;
            this.rootPath = rootPath;
        }

        @Override
        protected Result compute() {
            IOSearcher searcher = new IOSearcher();
            try {
                return searcher.search(text, rootPath);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

