package kengine;


/**
 * @overview A record <code><Doc,cnt></code> where <code>Doc</code> is a document object
 *           and <code>cnt</code> is an integer count of occurrences of some keyword in <code>Doc</code>.
 *           
 *           This class also implements a {@link Comparable} interface to support sorting.
 *           
 * @see      "Program development in Java", pgs 329-331,334
 * @note     unlike the strict record-type explanation in the text book, 
 *           we use setter and getter methods to implement observers and some mutator methods 
 *           of this class 
 * 
 * @version  4.0  add implementation for comparable interface
 *           
 * @author dmle 
 *
 */
public class DocCnt implements Comparable {
  private Doc d;
  private int cnt;
  
  public DocCnt(Doc d, int cnt) {
    this.d = d;
    this.cnt = cnt;
  }
  
  public Doc getDoc() {
    return d;
  }
  
  public int getCount() {
    return cnt;
  }
  
  public void addCount(int newCount) {
    cnt += newCount;
  }
  
  public String toString() {
    return "<" + d.title() + "," + cnt + ">";
  }
  
  /**
   * A method to compare <code>this</code> with another <code>DocCnt</code>.
   * 
   * @param x another <code>DocCnt</code> object
   * @effects If <code>x</code> is <code>null</code> throws <code>NullPointerException</code>; 
   *          if <code>x</code> is not an instance of <code>DocCnt</code> throws <code>ClassCastException</code>; 
   *          otherwise if <code>this.cnt < x.cnt</code> returns -1, if <code>this.cnt = x.cnt</code> returns 0, 
   *          else returns 1. 
   */
  public int compareTo(Object x) throws ClassCastException, NullPointerException {
    if (x == null) throw new NullPointerException("DocCnt.compareTo: argument is null");
    
    if (!(x instanceof DocCnt)) 
      throw new ClassCastException("DocCnt.compareTo: argument is not a DocCnt object");
    
    DocCnt dc = (DocCnt) x;
    if (this.cnt < dc.cnt)
      return -1;
    else if (this.cnt == dc.cnt) 
      return 0;
    else 
      return 1;
  }
  
  public Object clone() {
    // only clone the doc count, keep the reference to the doc object unchanged 
    return new DocCnt(this.d, cnt);
  }
}
