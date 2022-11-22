import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class IOSearcher implements TextSearcher, Result {


    @Override
    public Result search(String text, String rootPath) throws FileNotFoundException {
        String wordToFind = text;
        String path = rootPath;//C:\Users\Moshe Sayada\IdeaProjects\TextSearchEngine\data
        if (path.indexOf(".txt") != -1) {
            File file = new File(path);
            Scanner scan = new Scanner(file);
            int lineNum = 1;
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.indexOf(text) != -1) {
                    System.out.println(file.getName() + " line number : " + lineNum + " " + line);

                }
                lineNum++;
            }
        }
        else
        {

            File file = new File(path);
            for (File s : file.listFiles()){
                System.out.println(s.getAbsolutePath());
            }
            search(text , file.getAbsolutePath());
        }

        return null;
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
    }
}
