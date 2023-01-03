import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class IOSearcher implements TextSearcher  {

    public class ResultClass implements Result{
        public   String Query;
        public Map<String, Set<String>> Answer;

        public ResultClass() {
            Answer = new HashMap<>();

        }

        @Override
        public String getQuery() {

            return Query;
        }


        @Override
        public Map<String, Set<String>> getAnswer() {
            return Answer;
        }
    }

     public IOSearcher() {

    }
    @Override
    public Result search(String text, String rootPath) throws FileNotFoundException {
        ResultClass r = new ResultClass();
        r.Query = text;
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
                        System.out.println(file.getName() + ", line number "+ lineNum + ": ***" + line + "***");
                        set.add("line number " + lineNum + ":" + line);

                    }
                    lineNum++;
                }
                r.Answer.put(path, set);

            } else {

                File file = new File(path);
                if (file.listFiles() == null)
                    return r;
                for (File s : file.listFiles()) {
//                System.out.println(s.getAbsolutePath());

                    search(text, s.getAbsolutePath());
                }

            }
        } catch (Exception e){
            System.out.println(e.getMessage() + " Exception ");

        }


        return r;
    }

}
