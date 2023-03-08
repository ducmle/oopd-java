package kengine;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import utils.NotPossibleException;

/**
 * @overview An engine has a state as described in the search engine data model. 
 *           The methods throw the NotPossibleException when there is a problem; the exception
 *           contains a string explaining the problem. All instance methods modify the state of
 *           <code>this</code>.
 *           
 * 
 * @see "Program Development in Java", pgs: 313, 316-323, 365
 * 
 * @version 4.0 implement the full logic
 * @author dmle
 *
 */
public class Engine {
  private TitleTable tt;
  private WordTable wt;
  private Query q;
  
  //dmle: use Vector instead of array to ease maintenance  
  // private String[] urls;
  private Vector urls;

  /**
   * Constructor method 
   * 
   * @effects if uninteresting words cannot be retrieved from the persistent state
   *          throw <code>NotPossibleException</code> else creates NK and initialises
   *          the application state appropriately
   */
  public Engine() throws NotPossibleException {
    tt = new TitleTable();
    // the exception is thrown by this line
    wt = new WordTable();
    urls = new Vector();
  }

  /**
   * A method to create a <code>Query</code> object containing the matching documents 
   * of a given keyword <code>w</code>
   * 
   * @param w   a keyword to search
   * @effects   if <code>w</code> is not a word or <code>w</code> is an uninteresting word 
   *            then throws <code>NotPossibleException</code>, else returns 
   *            a <code>Query</code> object containing the documents matching the keyword
   * @version 4.0
   */
  public Query queryFirst(String w) throws NotPossibleException {
    if (w != null) 
      w = Helpers.canon(w);
    
    // check w
    if (wt.lookup(w) == null) {
      throw new NotPossibleException("Engine.queryFirst: the specified word is either not found in any documents or uninteresting: " + w);
    }
    
    q = new Query(wt, w);
    return q;
  }
  
  /**
   * A method to query the matching documents of an existing <code>Query</code> object for  
   * those that contains an additional keyword <code>w</code>
   * 
   * @param w   a keyword to search
   * @effects   if <code>w</code> is not a word or <code>w</code> is an uninteresting word 
   *            then throws <code>NotPossibleException</code>, else returns 
   *            an updated <code>Query</code> object containing the documents matching all keywords
   * @version 4.0
   */
  public Query queryMore(String w) throws NotPossibleException {
    if (w != null) 
      w = Helpers.canon(w);

    // check w
    if (wt.lookup(w) == null) {
      throw new NotPossibleException("Engine.queryMore: the specified word is either not found in any documents or uninteresting: " + w);
    }

    q.addKey(w);
    
    return q;
  }
  
  /**
   * A method to retrieve a <code>Doc</code> given its title.
   * 
   * @param t   the title of the document to retrieve
   * @effects   if <code>t</code> is not in <code>TitleTable</code>  
   *            then throw <code>NotPossibleException</code>, 
   *            else return the <code>Doc</code> object with title <code>t</code>
   */
  public Doc findDoc(String t) throws NotPossibleException {
    Doc d = tt.lookup(t);
    
    if (d == null) {
      throw new NotPossibleException("Engine.findDoc: the specified title could not be found: " + t);
    }
    
    return d;
  }
  
  /**
   * A method to retrieve documents from remote web site <code>u</code> and store 
   * them for query processing.
   * 
   * @param u   the URL of a remote web site
   * @effects   if <code>u</code> is not a URL for a web site containing documents or 
   *            <code>u</code> is one of the existing URLs throws 
   *            <code>NotPossibleException</code>, else adds each new document to 
   *            <code>TitleTable</code> and <code>WordTable</code> using their 
   *            respective methods. If no query was in progress then return an empty
   *            <code>Query</code> object, else returns an updated object that contains 
   *            any matching new documents.
   * @version 4.0  add each new document to the current query (if one exists)
   */
  public Query addDocs(String u) throws NotPossibleException {
    if (urls.contains(u)) 
      throw new NotPossibleException("Engine.addDocs: URL has been used: " + u);
        
    // use Comm.getDocs to obtain documents
    // this method will throw exception if u is not a valid URL
    Iterator docs = Comm.getDocs(u);
    Doc d;
    Hashtable h;
    while (docs.hasNext()) {
      d = (Doc) docs.next();
      //addDoc(d);
      tt.addDoc(d);
      h = wt.addDoc(d);
      
      if (q != null) {        
        q.addDoc(d, h);
      }
    }
    
    if (q == null) {
      q = new Query();
    }
    
    // stores URL to urls 
    urls.add(u);
    
    return q;
  }  

  /**
   * @effects 
   *  if tt is empty
   *    return null
   *  else
   *    return Iterator(Doc) for documents in tt
   *     
   * @version 5.0 (for use in the assignment)
   */
  public Iterator<Doc> docIterator() {
    if (tt.isEmpty()) {
      return null;
    } else {
      return tt.docIterator();
    }
  }
  
  /**
   * A method to return all none-keywords in as a string for display.
   * 
   * @effects return a string containing all none-keywords
   * @note this method is not in the original design of this class
   */
  public String getNonkeys() {   
    return wt.getNonkeys();
  }
  
  /**
   * A method to return the displayable content of the word table as string.
   *  
   * @effects return a string containing all the words and their <code>DocCnt</code> objects
   * @note this method is not in the original design of this class
   */
  public String getWordTableAsString() {
    return wt.toString();
  }
}
