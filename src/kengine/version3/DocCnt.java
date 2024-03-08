package kengine.version3;

import kengine.Doc;

/**
 * @overview A record <code><Doc,cnt></code> where <code>Doc</code> is a document object
 *           and <code>cnt</code> is an integer count of occurrences of some keyword in <code>Doc</code>.
 *           Note that <code>DocCnt</code> is immutable because the engine only supports 
 *           a single instance of a document (based on its title).
 *           
 *           This class also implements a {@link Comparable} interface to support sorting.
 *           
 * @see      "Program development in Java", pgs 329-331,334
 * @version  3.0  implements only the record format, not the comparable interface.
 *           
 * @author dmle 
 *
 */
public class DocCnt {
  private Doc d;
  private int cnt;
  
  public DocCnt(Doc d, Integer cnt) {
    this.d = d;
    this.cnt = cnt;
  }
  
  public Doc getDoc() {
    return d;
  }
  
  public int getCount() {
    return cnt;
  }
  
  public String toString() {
    return "<" + getDoc().title() + "," + getCount() + ">";
  }
}
