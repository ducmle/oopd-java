package kengine.version1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import utils.NotPossibleException;


/**
 * @overview Keeps track of both interesting and uninteresting words.
 *           Uninteresting words are mapped to </code>null</code>, while each
 *           interesting word is mapped to a <code>Vector</code> of
 *           <code>DocCnt</code> objects, each of which is a pair of document
 *           and the number of occurrences of the keyword in that document. The
 *           uninteresting words are obtained from a private file. 
 * 
 * @see "Program development in Java", pgs 320, 330, 365
 * @version 1.0
 * @author dmle
 * 
 */
public class WordTable {
  // the rep of this class
  private Hashtable table;

  private static final String NK_FILE = "nk.dat";

  /**
   * Constructor method
   * 
   * @effects If the file cannot be read throws
   *          <code>NotPossibleException</code>, else initialises the table to
   *          contain all the words in the file as uninteresting words.
   * @version 1.0 
   */
  public WordTable() throws NotPossibleException {
    table = new Hashtable();

    // read the NK file and store keywords to this table
    // assumes file is stored in the same directory as this class
    BufferedReader bf = new BufferedReader(new InputStreamReader(this
        .getClass().getResourceAsStream(NK_FILE)));

    try {
      if (!bf.ready())
        throw new NotPossibleException(
            "WordTable.init(): Failed to read non-key file " + NK_FILE);
    } catch (IOException ex) {
      throw new NotPossibleException(
          "WordTable.init(): Failed to read non-key file " + NK_FILE
              + "due to " + ex);
    }

    String nw;
    boolean eof = false;
    while (!eof) {
      try {
        nw = bf.readLine();
        if (nw != null) {
          nw = nw.trim();
          table.put(nw, "null");
        } else {
          eof = true;
        }
      } catch (IOException ex) {
        // failed to read a line, ignore and continue
      }
    }
  }

  /**
   * A method to add a document <code>d</code> to <code>this</code>.
   * 
   * @param d
   *          a string representing the document text
   * @requires <code>d</code> is not <code>null</code>
   * @modifies <code>this</code>
   * @effects Adds all interesting words of <code>d</code> to <code>this</code>
   *          with a count of their number of occurrences; returns a table
   *          mapping each interesting word in <code>d</code> to its number of
   *          occurrences.
   * 
   * @version 1.0 simply add document to an array
   */
  public Hashtable addDoc(Doc d) {
    return null;
  }

  /**
   * A method to check if a word <code>w</code> is an interesting one.
   * 
   * @param w
   *          A word
   * @effects If <code>w</code> is <code>null</code> or a nonword or an
   *          uninteresting word returns <code>false</code> else returns
   *          <code>true</code>.
   * @version 1.0 returns <code>false</code> since all words are assumed
   *          uninteresting.
   */
  public boolean isInteresting(String w) {
    return false;
  }

  /**
   * A method to look up all documents containing a keyword.
   * 
   * @param k
   *          a keyword to look up
   * @requires <code>k</code> is not <code>null</code>
   * @effects Returns a vector of <code>DocCnt</code>s where <code>Doc</code>
   *          contains <code>k</code> <code>cnt</code> times.
   * @version 1.0 returns <code>null</code> since all words are assumed
   *          uninteresting
   */
  public Vector lookup(String k) {
    return null;
  }

  /**
   * A method to return all none-keywords in as a string for display
   * 
   * @effects return a string containing all none-keywords of <code>this</code>
   * @note this method is not in the original design of this class
   */
  public String getNonkeys() {
    StringBuffer sb = new StringBuffer();
    for (Enumeration e = table.keys(); e.hasMoreElements();) {
      String w = (String) e.nextElement();
      Object v = lookup(w);
      if (v == null) {
        sb.append(w).append(" ");
      }
    }

    if (sb.length() > 0) {
      sb.delete(sb.length() - 1, sb.length());

      return sb.toString();
    } else
      return null;
  }
}
