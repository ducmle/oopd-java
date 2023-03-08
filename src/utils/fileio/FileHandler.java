package utils.fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import utils.NotPossibleException;

public class FileHandler {
  /**
   * @requires <tt>path is a text file</tt>
   * @effects <pre>
   *     if path is null
   *      throws NullPointerException
   *     else if path does not exist OR 
   *      failed to read file at the specified path 
   *      throws NotPossibleException 
   *     else 
   *      return a string containing the content of the file 
   *      at the specified path
   *      </pre> 
   */
  public static String getFile(String path) 
  throws NullPointerException, NotPossibleException {
    
    if (path == null)
      throw new NullPointerException("FileHandler.getFile: path is null");
    
    final String linesep = System.getProperty("line.separator");

    try {
      BufferedReader reader = new BufferedReader(
          new FileReader(new File(path)));
      
      // read file content
      StringBuffer docBuffer = new StringBuffer();
      String line = reader.readLine();
      while (line != null) {
        docBuffer.append(line).append(linesep);
        line = reader.readLine();
      }
      return docBuffer.toString();
    } catch (IOException e) {
      throw new NotPossibleException("FileHandler.getFile: failed to read file " + path);
    }
  }
}
