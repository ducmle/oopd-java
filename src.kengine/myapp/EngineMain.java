package myapp;

import java.io.File;
import java.util.Arrays;

import kengine.Engine;
import kengine.Query;
import utils.NotPossibleException;

/**
 * @overview
 *
 * @author Duc Minh Le (ducmle)
 *
 * @version
 */
public class EngineMain {
  public static void main(String[] args) {
    // initialise the engine
    Engine eng = new Engine();

    // initialise docs
    String sep = File.separator;
    String dirUrlPrefix = "file:" + sep + sep;
    String docDirPath = "/data/projects/usth/SE/2021/data/dochanu";
    String docDirUrl = dirUrlPrefix + docDirPath;
    // add docs from the data directory

    System.out.println("Adding docs from URL: " + docDirUrl);
    try {
      eng.addDocs(docDirUrl);
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
    
    // ask queries
    try {
      Query q = eng.queryFirst("sinh");
      
      printMatches(q);
      
      eng.queryMore("viên");
      
      printMatches(q);
      
    } catch (NotPossibleException e) {
      e.printStackTrace();
    }
    
  }

  /**
   * @effects 
   * 
   * @version 
   * 
   */
  private static void printMatches(Query q) {
    System.out.printf("Query: %s%n", Arrays.toString(q.keys()));
    for (int i = 0; i < q.size(); i++) {
      System.out.printf("%d: %s%n", i+1, q.fetch(i).title());
    }
  }
}
