package misc.comparableCollection;

/**
 * @overview 
 *  A customised sub-type of {@link Comparable} that can be compared to other 
 *  objects of the same type.
 *  
 * @author Duc Minh Le (ducmle)
 *
 * @version 
 */
public class MyComparable implements Comparable<MyComparable> {

  private Integer value;
  
  public MyComparable(Integer value) {
    this.value = value;
  }
  
  @Override
  public int compareTo(MyComparable o) {
    // TODO : check o if null, etc.
    return value.compareTo(o.value);
  }
  
  @Override
  public String toString() {
    return value+"";
  }
}
