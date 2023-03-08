package kengine.version1;

import java.util.Vector;

import utils.DuplicateException;
import utils.NotPossibleException;


/**
 * @overview  Keeps track of documents with their titles.
 * 
 * @see "Program development in Java", pgs 320, 365
 * 
 * @version 1.0 simply store documents in an array (actually we will use <code>Vector</code>
 *              to ease implementation).
 * 
 * @author dmle
 *
 */
public class TitleTable {
  
  private Vector docs;
  
  /**
   * Constructor method
   * @effects Initialises <code>this</code> to be an empty table.
   */
  public TitleTable() {
    docs = new Vector();
  }
  
  /**
   * A method to add a new <code>Doc</code> object to <code>this</code>.
   * 
   * @param d   A <code>Doc</code> object to add
   * @requires  <code>d</code> is not <code>null</code>
   * @effects   if a document with the same title already in <code>this</code> 
   *            throws <code>DuplicateException</code>, else adds <code>d</code> with 
   *            its title to <code>this</code>.
   * @version 1.0 ignores the duplicacy check and just adds <code>d</code>           
   */
  public void addDoc(Doc d) throws DuplicateException {
    docs.add(d);
  }
  
  /**
   * A method to look up a document given its title.
   * 
   * @param t   the title of the document to look up 
   * @effects   if <code>t</code> is <code>null</code> or there is no document with this 
   *            title throws <code>NotPossibleException</code>, else returns the document
   *            with title <code>t</code>.
   * @version 1.0 simply throws <code>NotPossibleException</code> since we donot yet store document and title 
   *              in a table
   */
  public Doc lookup(String t) throws NotPossibleException {
    throw new NotPossibleException("TitleTable.lookup: not implemented yet ");
  }
}
