package kengine.version1;

import java.util.Hashtable;
import java.util.Vector;

import utils.NotImplementedException;
import utils.NotPossibleException;


/**
 * @overview Represents a user query
 * 
 * @see   "Program development in Java", pgs 314,322,326-332,365
 * @version 1.0
 * @author dmle
 *
 */
public class Query {
  private WordTable wt;   // reference to the engine's word table
  private Vector matches; // document matches
  private String[] keys;  // the keywords of this query
  
  /**
   * Constructor method to create an empty <code>Query</code>
   * @version 1.0 only use this method to initialise a query
   */
  public Query() {
    //
  }
  
  /**
   * Constructor method to create a new <code>Query</code> from a single keyword
   * 
   * @param w  a keyword to create the query with
   * @param wt the <code>WordTable</code> object of the engine
   * @requires <code>w</code> and <code>wt</code> are not <code>null</code>
   * @effects  make a <code>Query</code> for the single keyword <code>w</code>. 
   *           The <code>Query</code> is initialised to have a maximum size specified by 
   *           <code>MAX_SIZE</code>.
   * @version  1.0  does not yet implement this method
   */
  public Query(WordTable wt, String w) {
    throw new NotImplementedException("Query.init(wt, w)");
  }
  
  /**
   * A method to add a new keyword to <code>this</code>.
   * 
   * @param w a new keyword to add to this query
   * @modifies  <code>this</code>
   * @effects If <code>this</code> is empty or <code>w</code> is an existingn keyword in 
   *          <code>this</code> throws <code>NotPossibleException</code>, else modifies 
   *          <code>this</code> to be a query for <code>w</code> and all the keywords already 
   *          in <code>this</code>.
   * @version 1.0 does not yet implement this method
   *          
   */
  public void addKey(String w) throws NotPossibleException {
    throw new NotImplementedException("Query.addKey");
  }
  
  /**
   * A method to add a new <code>Doc</code> object to <code>this</code>.
   * 
   * @param d   the <code>Doc</code> object to add
   * @param h   a <code>Hashtable</code> that maps interesting words in <code>d</code>
   *            to their frequencies in <code>d</code>.
   * @requires  <code>d</code> and <code>h</code> are not <code>null</code>
   * @modifies  <code>this</code>
   * @effects   If <code>this</code> is not empty and <code>d</code> contains all the keywords
   *            of <code>this</code> then adds <code>d</code> and its keyword entries in <code>h</code>
   *            to <code>matches</code> as a query result, else does nothing 
   *  
   * @version 1.0 does nothing since <code>Query</code> is assumed to 
   *              be empty 
   */
  public void addDoc(Doc d, Hashtable h) {
    //
  }
 
  /**
   * A method to read all the keywords of this query.
   * 
   * @effects returns all the keywords of <code>this</code>.
   * @version 1.0 returns <code>null</code> since <code>Query</code> is considered emtpy
   */
  public String[] keys() {
    return null;
  }
  
  /**
   * A method to return the number of keywords of this query. 
   * 
   * @effects returns a count of the documents that match the query
   * @version 1.0 returns 0 since <code>Query</code> is considered empty
   */
  public int size() {
    return 0;
  }
  
  /**
   * A method to return the matching document of this query.
   * 
   * @param i   the index of the matching document to return
   * @effects   if <code>0 <= i < size</code> then returns the ith matching document in <code>matches</code>, 
   *            else throws <code>IndexOutOfBoundsException</code>.
   * @version 1.0 returns <code>null</code> since <code>Query</code> is considered empty. 
   */
  public Doc fetch(int i) throws IndexOutOfBoundsException {
    return null;
  }
}
