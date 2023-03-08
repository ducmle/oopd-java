package kengine;

import java.util.Hashtable;
import java.util.Iterator;

import utils.DuplicateException;
import utils.NotPossibleException;

/**
 * @overview  Keeps track of documents with their titles.
 * 
 * @see "Program development in Java", pgs 320, 365
 * 
 * @version 
 * - 2.0: provides a full implementation <br>
 * - 5.0: improved to support generics 
 * 
 * @author dmle
 *
 */
public class TitleTable {
  
  // the rep of this class
  private Hashtable<String,Doc> docs;
  
  /**
   * Constructor method
   * @effects Initialises <code>this</code> to be an empty table.
   */
  public TitleTable() {
    docs = new Hashtable<>();
  }
  
  /**
   * A method to add a new <code>Doc</code> object to <code>this</code>.
   * 
   * @param d   A <code>Doc</code> object to add
   * @requires  <code>d</code> is not <code>null</code>
   * @effects   if a document with the same title already in <code>this</code> 
   *            throws <code>DuplicateException</code>, else adds <code>d</code> with 
   *            its title to <code>this</code>.
   * @version 2.0           
   */
  public void addDoc(Doc d) throws DuplicateException {
    String t = d.title();
    // canonical form
    t = Helpers.canon(t);
    
    if (docs.containsKey(t)) {
      throw new DuplicateException("TitleTable.addDoc: a document with same title already exists: " + t);
    }
    
    docs.put(t, d);
  }
  
  /**
   * A method to look up a document given its title.
   * 
   * @param t   the title of the document to look up 
   * @effects   if <code>t</code> is <code>null</code> or there is no document with this 
   *            title throws <code>NotPossibleException</code>, else returns the document
   *            with title <code>t</code>.
   * @version 2.0 
   */
  public Doc lookup(String t) throws NotPossibleException {
    Doc d = null;
    if (t != null) {
      // canonical form
      String ct = Helpers.canon(t);

      d = docs.get(ct);
    }
    
    if (d == null)
      throw new NotPossibleException("TitleTable.lookup: could not look up document with title " + t);
    else 
      return d;
  }

  /**
   * @effects 
   *  if this is empty
   *    return null
   *  else
   *    return Iterator(Doc) for documents in this
   *     
   * @version 5.0 (for use in the assignment)
   */
  public Iterator<Doc> docIterator() {
    if (isEmpty())
      return null;
    else
      return docs.values().iterator();
  }

  /**
   * @effects 
   *  if this is empty
   *    return true
   *  else
   *    return false
   *     
   * @version 5.0 (for use in the assignment)
   */
  public boolean isEmpty() {
    return docs.isEmpty();
  }
}
