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
public class ComparableDemo {
  public static void main(String[] args) {
    /*
     initialise a collection of Comparable objects
     */
    Collection<Comparable> col = new TreeSet<>(); 
        
    //    add some built-in Comparable objects to the collection
    col.add(new MyComparable(1));
    col.add(new MyComparable(100));
    col.add(new MyComparable(51));
    col.add(new MyComparable(73));
    col.add(new MyComparable(11));
    
    //    Print the collection, observe that objects are sorted in the natural order
    System.out.printf("Sorted collection: %s%n", col);
    
    //    (extended) design a customise Comparable class (MyComparable)
    MyComparable c1 = new MyComparable(1000),
                 c2 = new MyComparable(-20);
    //    add MyComparable objects to the collection, observe the result
    col.add(c1);
    col.add(c2);
    
    System.out.printf("...with MyComparable: %s%n", col);
    
    // try removing some elements...
    col.remove(c2);
    col.remove(c1);
    System.out.printf("...after removal: %s%n", col);

  }
}
