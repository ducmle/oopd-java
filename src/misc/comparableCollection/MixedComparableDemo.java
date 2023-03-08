package misc.comparableCollection;

import java.util.Collection;
import java.util.TreeSet;

/**
 * @overview 
 *  Demonstrate how to use {@link Comparable} with the {@link Collection} framework.
 *  
 * @author Duc Minh Le (ducmle)
 *
 * @version 
 */
public class MixedComparableDemo {
  public static void main(String[] args) {
    /*
     initialise a collection of Comparable objects
     */
    Collection<Comparable> col = new TreeSet<>(); 
        
    //    add some built-in Comparable objects to the collection
    col.add(1);
    col.add(100);
    col.add(51);
    col.add(73);
    col.add(11);
    
    //    Print the collection, observe that objects are sorted in the natural order
    System.out.printf("Sorted collection: %s%n", col);
    
    //    (extended) design a customise Comparable class (MyComparable)
    MyAllComparable c1 = new MyAllComparable(1000),
                 c2 = new MyAllComparable(-20);
    //    add MyComparable objects to the collection, observe the result
    col.add(c1);
    col.add(c2);
    
    System.out.printf("...with MyAllComparable: %s%n", col);
    
    // try removing some elements...
    col.remove(11);
    col.remove(c1);
    System.out.printf("...after removal: %s%n", col);

  }
}
