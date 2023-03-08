package misc.comparableCollection;

/**
 * @overview 
 *   A customised sub-type of {@link Comparable} that can be compared to any
 *   other {@link Comparable}, not necessarily of the same type.
 *   For example, a {@link MyComparable} object can be compared with an Integer, a String
 *   or another {@link MyComparable} object.
 *   
 * @author Duc Minh Le (ducmle)
 *
 * @version 
 */
public class MyAllComparable implements Comparable<Comparable> {

  private Integer value;
  
  public MyAllComparable(Integer value) {
    this.value = value;
  }
  
  /* (non-Javadoc)
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  /**
   * @effects 
   * 
   * @version 
   */
  @Override
  public int compareTo(Comparable o) {
    // TODO : check o if null, etc.
    // need to invoke on o to use Comparable.compareTo method
    // so need to reverse the result (-1)
    return -1 * o.compareTo(value);
  }
  
  @Override
  public String toString() {
    return value+"";
  }
}
