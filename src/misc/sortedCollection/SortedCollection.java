package misc.sortedCollection;

import lect01_oopano.ch5.collections.LinkedList;
import utils.DOpt;
import utils.OptType;

/**
 * @overview This example demonstrates how to design a sorted collection.
 * 
 * <b>Requires: LinkedList class from the lecture source code.
 * 
 * @author Duc Minh Le (ducmle)
 */
public class SortedCollection {
  public static void main(String[] args) {
    MySortedCollection myCol = new MySortedCollection();  
    
    int[] elements = {100, 11, 20, -5, 6, 2};
    
    for (int num : elements) {
      myCol.add(num);
    }
    
    // TODO: not sorted
    System.out.println(myCol);
  }  
}

class MySortedCollection extends LinkedList<Comparable> {
  @Override
  @DOpt(type=OptType.MutatorAdd)
  public void add(Comparable x) {
    // TODO: sort x in this so that it is added to the correct position
  }
  
  @Override
  @DOpt(type=OptType.MutatorRemove)
  public void remove(Comparable x) {
    // TODO: update this's sorting after x is removed
  }
}

