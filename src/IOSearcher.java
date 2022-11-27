import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.*;

public class IOSearcher implements TextSearcher, Result {

    private String query;
    private Map<String,Set<String>> answer = new HashMap<>();
    @Override
    public Result search(String text, String rootPath) throws FileNotFoundException {
          query = text;
        String path = rootPath;
        Set<String> set = new HashSet<>();


        try {


            if (path.lastIndexOf(".txt") != -1) {
                File file = new File(path);
                Scanner scan = new Scanner(file);
                int lineNum = 1;
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    if (line.indexOf(text) != -1) {
                        System.out.println(file.getName() + " line number : " + lineNum + " " + line);
                        set.add("line number " + lineNum + ":" + line);

                    }
                    lineNum++;
                }
                answer.put(path, set);

            } else {

                File file = new File(path);
                if (file.listFiles() == null)
                    return this;
                for (File s : file.listFiles()) {
//                System.out.println(s.getAbsolutePath());

                    search(text, s.getAbsolutePath());
                }

            }
        } catch (Exception e){
            System.out.println("exception");

        }


        return this;
    }

    @Override
    public String getQuery() {

        return query;
    }

    public void setQuery(String query) {
        query = query;
    }

    public void setAnswer(Map<String, Set<String>> answer) {
        answer = answer;
    }

    @Override
    public Map<String, Set<String>> getAnswer() {
        return answer;
    }
    public static void main(String[] args) throws FileNotFoundException {
        IOSearcher io = new IOSearcher();
        String path = "//C:\\Users\\Moshe Sayada";
        String text = "moshe sayada";
       Result r = io.search(text,path);
        Map<String,Set<String>> answer_ = r.getAnswer();
        for (Map.Entry<String,Set<String>> entry : answer_.entrySet())
        {
            if(!entry.getValue().isEmpty())
            {
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
            }
        }


    }
}
