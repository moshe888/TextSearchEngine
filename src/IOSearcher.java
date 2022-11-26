import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.*;

public class IOSearcher implements TextSearcher, Result {

    private String query;
    private Map<String,Set<String>> answer = new HashMap<>();
    @Override
    public Result search(String text, String rootPath) throws FileNotFoundException {
        String query = text;
        String path = rootPath;
        Set<String> set = new HashSet<>();



         if (path.lastIndexOf(".txt") != -1) {
            File file = new File(path);
            Scanner scan = new Scanner(file);
            int lineNum = 1;
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.indexOf(text) != -1) {
                    System.out.println(file.getName() + " line number : " + lineNum + " " + line);
                    set.add(line);

                }
                lineNum++;
            }
             answer.put(path, set);

         }
        else
        {

            File file = new File(path);
            for (File s : file.listFiles()){
                System.out.println(s.getAbsolutePath());
            }
            search(text , file.getAbsolutePath());
        }
        ResultClass res = new ResultClass();
        res.setQuery(query);
        res.setAnswer(answer);
        return res;
    }

    @Override
    public String getQuery() {
        return null;
    }

    @Override
    public Map<String, Set<String>> getAnswer() {
        return null;
    }
    public static void main(String[] args) throws FileNotFoundException {
        IOSearcher io = new IOSearcher();
        String path = "//C:\\Users\\Moshe Sayada\\IdeaProjects\\TextSearchEngine\\data\\a1.txt";
        String text = "dog";
       Result r = io.search(text,path);
        Map<String,Set<String>> answer = r.getAnswer();
         System.out.println(answer.get(path));


    }
}
