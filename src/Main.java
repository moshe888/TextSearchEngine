import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        TextSearcher io =new CacheIOSearcher(new IOSearcher());
         while (true) {
             Scanner scan = new Scanner(System.in);
             System.out.println("enter path:");
             String path = scan.nextLine();
             System.out.println("enter text search:");
             String text = scan.nextLine();


             Result r = io.search(text, path);
             System.out.println(r.getQuery());
         }

         }
    }


