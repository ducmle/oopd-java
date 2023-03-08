package kengine.version2;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import kengine.version1.Comm;
import kengine.version1.Doc;
import kengine.version1.Query;
import kengine.version1.WordTable;
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
 * @version 2.0 
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
   * @version 1.0 returns empty <code>Query</code> object since all words are assumed uninteresting
   */
  public Query queryFirst(String w) throws NotPossibleException {
    // canonical form
    String cw = Helpers.canon(w);
    
    // check w
    if (wt.lookup(cw) == null) {
      throw new NotPossibleException("Engine.queryFirst: the specified word is either not found in any documents or uninteresting: " + w);
    }
    
    q = new Query();
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
   * @version 2.0 returns empty <code>Query</code> object since all words are assumed uninteresting
   */
  public Query queryMore(String w) throws NotPossibleException {
    // canonical form
    String cw = Helpers.canon(w);

    // check w
    if (wt.lookup(cw) == null) {
      throw new NotPossibleException("Engine.queryMore: the specified word is either not found in any documents or uninteresting: " + w);
    }

    q = new Query();
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
   * @version 1.0  returns empty <code>Query</code> object since all keywords are assumed uninteresting
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
      tt.addDoc(d);
      h = wt.addDoc(d);
      
      // debug
      System.out.println("added: " + d.title());

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
   * A method to return all none-keywords in as a string for display.
   * 
   * @effects return a string containing all none-keywords
   * @note this method is not in the original design of this class
   */
  public String getNonkeys() {   
    return wt.getNonkeys();
  }
}
