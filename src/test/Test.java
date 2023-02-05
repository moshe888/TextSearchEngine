package test;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {


    public static void main(String[] args) throws FileNotFoundException {
        IOSearcher.IOSearcherImpl searcher = new IOSearcher().new IOSearcherImpl();
//        IOSearcher.Result result = searcher.search("dog", "C:\\Users\\Moshe Sayada\\IdeaProjects\\TextSearchEngine\\data");
//        System.out.println("Result: " + result.getQuery() + result.getAnswer());

        CacheIOSearcher.CacheIOSearcherImpl cacheSearcher = new CacheIOSearcher().new CacheIOSearcherImpl( searcher);
         while (true) {
             Scanner scan = new Scanner(System.in);
             System.out.println("enter path:");
             String path = scan.nextLine();
             System.out.println("enter text search:");
             String text = scan.nextLine();

             IOSearcher.Result r = cacheSearcher.search(text, path);
             System.out.println(r.getAnswer().get("a1.txt"));
          }

    }
}



