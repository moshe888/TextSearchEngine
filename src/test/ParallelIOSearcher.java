package test;

import java.io.FileNotFoundException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIOSearcher implements IOSearcher.TextSearcher {

    public final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

    @Override
    public IOSearcher.Result search(String text, String rootPath) {
        SearchTask task = new SearchTask(text, rootPath);
        return FORK_JOIN_POOL.invoke(task);
    }

    private static class SearchTask extends RecursiveTask<IOSearcher.Result> {
        private String text;
        private String rootPath;

        public SearchTask(String text, String rootPath) {
            this.text = text;
            this.rootPath = rootPath;
        }

        @Override
        protected IOSearcher.Result compute() {
            IOSearcher.IOSearcherImpl searcher = new IOSearcher().new IOSearcherImpl();
            try {
                return searcher.search(text, rootPath);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

