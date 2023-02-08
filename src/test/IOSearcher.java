package test;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IOSearcher implements TextSearcher {

    @Override
    public Result search(String text, String rootPath) throws FileNotFoundException {
        Result result = new ResultImpl(text, new HashMap<>());

        File root = new File(rootPath);
        searchRecursive(root, text, result);
        return result;
    }

    private void searchRecursive(File file, String text, Result result) {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                searchRecursive(f, text, result);
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
                    result.getAnswer().put(file.getName(), lines);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


       class ResultImpl implements Result {
        private final String query;
        private final Map<String, Set<String>> answer;

        public ResultImpl(String query, Map<String, Set<String>> answer) {
            this.query = query;
            this.answer = answer;
        }

        @Override
        public String getQuery() {
            return query;
        }

        @Override
        public Map<String, Set<String>> getAnswer() {
            return answer;
        }
    }




interface TextSearcher {
    Result search(String text, String rootPath) throws FileNotFoundException;
}


interface Result {
    String getQuery();
    Map<String, Set<String>> getAnswer();
}

