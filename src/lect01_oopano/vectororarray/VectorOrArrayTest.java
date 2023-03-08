package lect01_oopano.vectororarray;

import java.util.Vector;

/**
 * @overview 
 *  Test the performance of of int[] and Vector<Integer> in some key operations
 *  
 * @author Duc Minh Le (ducmle)
 *
 * @version 
 */
public class VectorOrArrayTest {
  
  public static void main(String[] args) {
    // test the performance of int[] and Vector<Integer> in some key operations
    int[] arr = {1,5,9,1000, 0, 2000,-5, -10};
    Vector<Integer> vect = new Vector<>();
    for (int e : arr) vect.add(e);
    
    // insertion
    int x = 150;
    int index = 3;
    double start = System.nanoTime();
    arr[index] = x;
    double end = System.nanoTime();
    double t1 = (end-start)/1000; 
    
    start = System.nanoTime();
    vect.set(index, x);
    end = System.nanoTime();
    double t2 = (end-start)/1000; 
    System.out.printf("array.insert: %.3f (millis); vector.insert: %.3f (millis)%n", 
        t1, t2);
    
    // addition
    int y = 222;
    start = System.nanoTime();
    int[] arr1 = new int[arr.length + 1];
    System.arraycopy(arr, 0, arr1, 0, arr.length);
    arr1[arr.length] = y;
    end = System.nanoTime();
    t1 = (end-start)/1000; 
    
    start = System.nanoTime();
    vect.add(y);
    end = System.nanoTime();
    t2 = (end-start)/1000; 
    System.out.printf("array.add: %.3f (millis); vector.add: %.3f (millis)%n", 
        t1, t2);
    
    // isIn
    x = -555;
    start = System.nanoTime();
    end = -1;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == x) {
        end = System.nanoTime();
        break;
      }
    }
    if (end < 0) end = System.nanoTime();
    t1 = (end-start)/1000; 
    
    start = System.nanoTime();
    vect.contains(x);
    end = System.nanoTime();
    t2 = (end-start)/1000; 
    System.out.printf("array.isIn: %.3f (millis); vector.isIn: %.3f (millis)%n", 
        t1, t2);
  }
  
}
