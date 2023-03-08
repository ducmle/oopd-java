package lect01_oopano.apps;

import java.util.Vector;

public class IntVector {
  public static void main(String[] args) {
    Vector<Integer> v = new Vector<>(); // creates a new empty Vector
    
    // adds integers to the vector
    v.add(1);  // first element at index 0
    v.add(1);
    v.add(2);
    v.add(3);
    v.add(5);
    
    // prints
    System.out.printf("Vector: %s%n", v);
    
    // retrieve an element at a given index
    int i = (Integer) v.get(0); // casting is required
        
    // prints
    System.out.printf("element 0: %d%n", i);

    // change a particular element
    v.set (0, -1);
    
    // prints
    System.out.printf("after set(0,-1): %s%n", v);
    
    // delete an element at a given index
    v.remove(0);
    
    // prints
    System.out.printf("after remove(0): %s%n", v);    
  }
}
