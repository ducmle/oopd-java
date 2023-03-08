package lect01_oopano.apps;

import java.util.Vector;

public class Vectors {
  public static void main(String[] args) {
    Vector<String> v = new Vector<>(); // creates a new empty Vector
    
    // adds an element to the end
    v.add("abc");  // first element at index 0
    
    // retrieve an element at a given index
    String s = (String) v.get(0); // casting is required
    
    // throws IndexOutOfBoundsException
    //String t = (String) v.get(1);
    
    // change a particular element
    v.set (0, "def");
    
    // delete an element at a given index
    v.remove(0);    
    
  }
}
