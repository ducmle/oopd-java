package kengine;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import utils.NotPossibleException;


/**
 * @overview Represents a user query
 * 
 * @see "Program development in Java", pgs 314,322,326-332,365
 * 
 * @version 4.0 implements full code
 * @author dmle
 * 
 */
public class Query {
  private WordTable wt; // reference to the engine's word table
  private Vector matches; // document matches
  // dmle: use Vector instead of array
  // private String[] keys; // the keywords of this query
  private Vector keys;

  /**
   * Constructor method to create an empty <code>Query</code>
   * 
   * @version 4.0
   */
  public Query() {
    //
    matches = new Vector();
    keys = new Vector();
  }

  /**
   * Constructor method to create a new <code>Query</code> from a single keyword
   * 
   * @param w
   *          a keyword to create the query with
   * @param wt
   *          the <code>WordTable</code> object of the engine
   * @requires <code>w</code> and <code>wt</code> are not <code>null</code>
   * @effects make a <code>Query</code> for the single keyword <code>w</code>.
   * 
   * @version 4.0
   * @see "Program development in Java", pg 329
   */
  public Query(WordTable wt, String w) {
    // call this to initialise rep
    this();
    
    this.wt = wt;

    // look up the key in the word table
    // sort the matches using quick sort
    Vector newDocs = wt.lookup(w);

    if (newDocs != null) {
      // must clone newDocs so that changes to the doc counts of its objects
      // do not affect the word table
      Vector docClones = new Vector();
      for (Iterator it = newDocs.iterator(); it.hasNext();) {
        DocCnt dc = (DocCnt) ((DocCnt) it.next()).clone();
        docClones.add(dc);
      }
      matches.addAll(docClones);
      keys.add(w);
      
      Sorting.quickSort(matches);
    }
  }

  /**
   * A method to add a new keyword to <code>this</code>.
   * 
   * @param w
   *          a new keyword to add to this query
   * @modifies <code>this</code>
   * @effects If <code>this</code> is empty or <code>w</code> is an existing
   *          keyword in <code>this</code> throws
   *          <code>NotPossibleException</code>, else modifies <code>this</code>
   *          to be a query for <code>w</code> and all the keywords already in
   *          <code>this</code>.
   * @version 4.0
   * @see "Program development in Java", pg 329
   * 
   */
  public void addKey(String w) throws NotPossibleException {
    if (matches.isEmpty() || keys.contains(w))
      throw new NotPossibleException(
          "Query.addKey: query is empty OR keyword already exists in query: "
              + w);

    keys.add(w);

    // look up the new query in word table
    // store the information about matches in a hash table
    Vector newDocs = wt.lookup(w);

    // for each current match, look up the document in the hash table and if it
    // is there, add it to the matches vector
    DocCnt currentMatch, newDoc;
    boolean foundNewMatch = false;

    for (int i = 0; i < matches.size(); i++) {
      currentMatch = (DocCnt) matches.get(i);
      boolean containsKeyword = false;  // whether or not the current match contains the new keyword
      INNER: for (Iterator dit = newDocs.iterator(); dit.hasNext();) {
        newDoc = (DocCnt) dit.next();
        if (currentMatch.getDoc().equals(newDoc.getDoc())) {
          // found a new match, update the sum of count in the vector
          currentMatch.addCount(newDoc.getCount());
          containsKeyword = true;
          if (!foundNewMatch)
            foundNewMatch = true;
          break INNER;
        }
      }
      
      if (!containsKeyword) {
        // remove current match
        matches.removeElementAt(i);
        i--;
      }
    }

    // sort the vector using quick-sort
    if (foundNewMatch) {
      Sorting.quickSort(matches);
    }
  }

  /**
   * A method to add a new <code>Doc</code> object to <code>this</code>.
   * 
   * @param d
   *          the <code>Doc</code> object to add
   * @param h
   *          a <code>Hashtable</code> that maps interesting words in
   *          <code>d</code> to their frequencies in <code>d</code>.
   * @requires <code>d</code> and <code>h</code> are not <code>null</code>
   * @modifies <code>this</code>
   * @effects If <code>this</code> is not empty and <code>d</code> contains all
   *          the keywords of <code>this</code> then adds <code>d</code> and its
   *          keyword entries in <code>h</code> to <code>matches</code> as a
   *          query result, else does nothing
   * 
   * @version 4.0
   */
  public void addDoc(Doc d, Hashtable h) {
    if (!keys.isEmpty()) {
      String k;
      Integer c;
      int sum = 0;
      for (Iterator kit = keys.iterator(); kit.hasNext();) {
        k = (String) kit.next();
        c = (Integer) h.get(k);
        if (c == null) {
          // d does not contain all keywords of this
          return;
        } else {
          sum += c.intValue();
        }
      }

      // if we get here then d satisfies the query
      DocCnt dc = new DocCnt(d, sum);
      matches.add(dc);
      DocCnt cm;

      // add dc to a position in the matches vector
      for (int i = 0; i < matches.size(); i++) {
        cm = (DocCnt) matches.get(i);
        if (cm.getCount() < dc.getCount()) {
          matches.insertElementAt(dc, i);
          break;
        }
      }
    }
  }

  /**
   * A method to read all the keywords of this query.
   * 
   * @effects returns all the keywords of <code>this</code>.
   * @version 4.0
   */
  public String[] keys() {
    return (String[]) keys.toArray(new String[keys.size()]);
  }

  /**
   * A method to return count of matching documents.
   * 
   * @effects returns a count of the documents that match the query
   * @version 4.0
   */
  public int size() {
    return matches.size();
  }

  /**
   * A method to return a matching document of this query.
   * 
   * @param i
   *          the index of the matching document to return
   * @effects if <code>0 <= i < size</code> then returns the ith matching
   *          document in <code>matches</code>, else throws
   *          <code>IndexOutOfBoundsException</code>.
   * @version 4.0
   */
  public Doc fetch(int i) throws IndexOutOfBoundsException {
    if (0 <= i && i < size()) {
      return ((DocCnt) matches.get(i)).getDoc();
    } else
      throw new IndexOutOfBoundsException(
          "Query.fetch: document index is invalid " + i);
  }
  
  /**
   * A method to return keywords and matches of <code>this</code> (if any) as string.
   * 
   * @version 4.0
   */
  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    if (keys != null && !keys.isEmpty()) {
      sb.append("Query: ");
      sb.append(keys.toString());
    }
    
    if (matches != null && !matches.isEmpty()) {
      sb.append("\nMatches [").append(matches.size()).append("]:\n");
      sb.append(matches.toString());
    }
    
    if (sb.length() > 0) 
      return sb.toString();
    else
      return null;
  } 
}
