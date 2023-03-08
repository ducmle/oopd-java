package lect01_oopano.ch3;

import java.util.Vector;

/**
 * @overview A class that provides a number of standalone procedures useful for manipulating
 *           Vectors
 *           
 * @author dmle
 *
 */
public class Vectors {
  /**
   * A method to remove all duplicate elements from a vector. 
   * 
   * @requires  all elements of <code>v</code> are not <code>null</code>
   * @modifies  <code>v</code>
   * @effects   Removes all duplicate elements from <code>v</code>; uses <code>equals</code> to 
   *            determine duplicates. The order of remaining elements may change.
   */
  public static void removeDupls(Vector v) {
    if (v == null) return;
    
    for (int i = 0; i < v.size(); i++) {
      Object x = v.get(i);
      
      int j = i + 1;
      // remove all dupls of x from the rest of v
      while(j < v.size()) {
        if (!x.equals(v.get(j))) 
          j++;
        else
          v.set(j, v.lastElement());
          v.remove(v.size()-1);
      }
    }
  }
}
