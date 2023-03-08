package lect01_oopano.apps;

import java.util.Arrays;

import lect01_oopano.IntSet;

/**
 * @overview  A program that creates a set of integers and displays its elements
 * @author dmle
 */
public class Integers { 
  /**
   * The run method.
   * 
   * @effects Creates a set of integers and displays its description
   */
  public static void main(String[] args) {
    int a[] = { 1, 1, 2, 2, 3, 5, 6, 8 };
    System.out.println("a: " + Arrays.toString(a));

    // create a set
    IntSet s = new IntSet();
    
    for (int i = 0; i < a.length; i++) {
      s.insert(a[i]);
    }
    
    System.out.println(s);  // s.toString()
    
    // remove elements from set
    s.remove(1);
    System.out.println("set after remove(1): " + s);
    
    s.remove(-1);
    System.out.println("set after remove(-1): " + s);

    // check validity    
    boolean valid = s.repOK();
    System.out.println("set is OK? " + valid);
    
  }
} // end Integers
