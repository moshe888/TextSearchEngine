package test;

import java.io.FileNotFoundException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIOSearcher extends IOSearcher {

    private final ForkJoinPool pool = new ForkJoinPool(4);

    @Override
    public Result search(String text, String rootPath) throws FileNotFoundException {
        Result result = new Result() {
            private final Map<String, Set<String>> answer = new HashMap<>();

            @Override
            public String getQuery() {
                return text;
            }
            @Override
            public String toString() {
                return text +": " +answer;
            }

            @Override
            public Map<String, Set<String>> getAnswer() {
                return answer;
            }
        };

        pool.invoke(new SearchTask(text, new File(rootPath), result));
        pool.shutdown();
        return result;
    }

    private static class SearchTask extends RecursiveTask<Void> {
        private final String text;
        private final File file;
        private final Result result;

        public SearchTask(String text, File file, Result result) {
            this.text = text;
            this.file = file;
            this.result = result;
        }

        @Override
        protected Void compute() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Thread " + Thread.currentThread().getName() + " is processing file " + file.getAbsolutePath());
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        SearchTask task = new SearchTask(text, f, result);
                        task.fork();
                        task.join();

                    }
                }
            } else if (file.getName().endsWith(".txt")) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    int lineNumber = 1;
                    Set<String> lines = new HashSet<>();
                    while ((line = reader.readLine()) != null) {
                        if (line.contains(text)) {
                            lines.add(String.valueOf(lineNumber));
                        }
                        lineNumber++;
                    }
                    if (!lines.isEmpty()) {
                        synchronized (result) {
                            result.getAnswer().put(file.getName(), lines);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}





//public class ParallelIOSearcher implements TextSearcher {
//
//    public final ForkJoinPool FORK_JOIN_POOL  ;
//    public ParallelIOSearcher(ForkJoinPool fork_join_pool) {
//         FORK_JOIN_POOL = fork_join_pool;
//     }
//
//    @Override
//    public Result search(String text, String rootPath) {
//        SearchTask task = new SearchTask(text, rootPath);
//        return FORK_JOIN_POOL.invoke(task);
//    }
//
//    private static class SearchTask extends RecursiveTask<Result> {
//        private String text;
//        private String rootPath;
//
//        public SearchTask(String text, String rootPath) {
//            this.text = text;
//            this.rootPath = rootPath;
//        }
//
//        @Override
//        protected Result compute() {
//            IOSearcher searcher = new IOSearcher();
//            try {
//                return searcher.search(text, rootPath);
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}
//
