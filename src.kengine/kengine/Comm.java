package kengine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import utils.NotPossibleException;


/**
 * @overview A communication library class that implements the method to obtain
 *           web documents from a given remote web site.
 *           
 * @version 1.0
 * 
 * @author dmle
 * 
 */
public class Comm {

  /**
   * A method to obtain documents from a remote web site <code>u</code>
   * 
   * @param u
   *          the URL of a remote web site
   * @effects if documents cannot be retrieved from the web site <code>u</code>
   *          throws <code>NotPossibleException</code>, else returns a generator
   *          for the documents
   * @version 1.0 assumes that <code>u</code> is the URL of a local directory
   *          which stores the web site documents. In other words, if
   *          <code>u</code> does not begin with <code>file://</code> protocol,
   *          throws <code>NotPossibleException</code>.
   * 
   */
  public static Iterator getDocs(String u) throws NotPossibleException {
    // if u is a local folder, reads its files as Doc objects
    // and returns a generator for them.

    int protocolInd = u.indexOf("/");
    String protocol = u.substring(0, protocolInd + 2); // the protocol part of u

    if (!protocol.equals("file://")) {
      throw new NotPossibleException(
          "Comm.getDocs: URL must be a file:// protocol: " + u);
    }

    String path = u.substring(protocolInd + 2); // folder path of u
    File dir = new File(path);
    if (!dir.exists() || !dir.isDirectory()) {
      // path is not a valid directory
      throw new NotPossibleException("Comm.getDocs: URL path is not valid: "
          + u);
    }

    File[] files = dir.listFiles();

    return new DocGenerator(files);
  }
}

/**
 * @overview A generator implementation for documents. 
 * 
 * @author dmle
 *
 */
class DocGenerator implements Iterator {
  Doc[] docs;
  int currIndex = -1;

  public DocGenerator(File[] files) throws NotPossibleException {
    docs = new Doc[files.length];

    File f;
    BufferedReader fr;
    String d, line;
    Doc doc;
    for (int i = 0; i < files.length; i++) {
      f = files[i];
      try {
        fr = new BufferedReader(new FileReader(f));
        d = "";
        while ((line = fr.readLine()) != null) {
          d += line + "\n";
        }
      } catch (IOException e) {
        throw new NotPossibleException(
            "Comm.DocGenerator.init: Failed to read file " + f);
      }
      doc = new Doc(d);
      docs[i] = doc;
    }
  }

  public boolean hasNext() {
    return currIndex < docs.length - 1;
  }

  public Object next() {
    currIndex++;
    return docs[currIndex];
  }
  
  public void remove() {
    // does nothing
  }
}
