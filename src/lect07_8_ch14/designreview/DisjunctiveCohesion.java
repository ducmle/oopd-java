package lect07_8_ch14.designreview;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class DisjunctiveCohesion {
  
  /**
   * @requires <tt>a is not null && a is not empty && 
   *    all elements of a are integers && 0 <= j < a.size</tt>
   * @effects <pre>
   *   if a is a List
   *    returns element a[j] 
   *   else
   *    returns the jth element in a.iterator</pre>
   */
  public static Object getElement(Collection a, int j) {
    return null;
  }
  
  /**
   * @requires <tt>a is not null</tt>
   * @effects <pre>
   *   if a is a List
   *    returns element at a random index of a 
   *   else
   *    returns an arbitrary element</pre>
   */
  public static Object getElement(Collection a) {
    if (a instanceof List) {
      return getRandom((List) a);
    } else {
      return getArbitrary(a.iterator());
    }
  }
  
  public static Object getRandom(List a) {
    return null;
  }
  
  public static Object getArbitrary(Iterator a) {
    return null;
  }

}
