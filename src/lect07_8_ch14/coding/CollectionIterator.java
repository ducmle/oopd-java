package lect07_8_ch14.coding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @overview 
 *  Demonstrates how to use {@link Iterator}.
 *  
 * @author Duc Minh Le (ducmle)
 *
 * @version 
 */
public class CollectionIterator {
  public static void main(String[] args) {
    // suppose you have a collection of objects
    Collection<String> col = new ArrayList<>();
    col.add("Hello");
    col.add("world");
    col.add("one");
    
    System.out.printf("Collection: %s%n", col);
    
    // and you want to iterate over the elements of the collection
    // -> the Iterator interface is provided for this purpose
    Iterator<String> it = col.iterator();
    while (it.hasNext()) {  // loop while there are more elements
      // get the next element 
      String element = it.next();
      // process the element
      System.out.printf("element: %s%n", element);
    }
  }
}
