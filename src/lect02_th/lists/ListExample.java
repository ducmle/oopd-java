package lect02_th.lists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListExample {
  public static void main(String[] args) {
    // an example to show how to use List.iterator
    
    // create a list
    List<Integer> list = new ArrayList<>();
    
    list.add(2);
    list.add(0);
    list.add(1);
    list.add(3);
    list.add(100);
    
    // print elements using iterator
    int sum = sum(list);
    System.out.println("Sum (before): " + sum);

    // remove some elements:
    int element = 3;
    list.remove(element);
    System.out.println("removed: " + element);

    sum = sum(list);
    System.out.println("Sum (after): " + sum);
  }

  /**
   * @effects 
   *  compute and return the arithmetic sum of elements of list
   */
  private static int sum(List<Integer> list) {
    Iterator<Integer> it = list.iterator();
    int e;
    int sum = 0;
    // System.out.println("List before loop: " + list);
    
    while (it.hasNext()) {
      e = it.next();
      
      sum = sum + e;
    }
    
    return sum;
  }
}
