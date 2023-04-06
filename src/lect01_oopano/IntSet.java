package lect01_oopano;

import java.util.Vector;
import utils.DOpt;
import utils.DomainConstraint;
import utils.OptType;
import utils.collections.Collection;

/**
 * @overview IntSet are mutable, unbounded sets of integers.
 * @attributes 
 *   elements   Set<Integer>  Vector<Integer>
 * @object A typical IntSet object is c={x1,...,xn}, where x1,...,xn are
 *   elements.
 * @abstract_properties
 *  optional(elements) = false /\ 
 *  for all x in elements. x is integer /\ 
 *  for all x, y in elements. x neq y
 * @author dmle
 */
public class IntSet implements Collection {
  @DomainConstraint(type = "Vector", optional = false)
  private Vector<Integer> elements; // use generic syntax 

  // constructor methods
  /**
   * @effects initialise <tt>this</tt> to be empty
   */
  public IntSet() {
    //
    elements = new Vector<>();
  }

  /**
   * @modifies <tt>this</tt>
   * @effects <pre>
   *   if x is already in this 
   *     do nothing 
   *   else 
   *     add x to this, i.e., this_post = this + {x}</pre>
   */
  @DOpt(type=OptType.MutatorAdd)
  public void insert(int x) {
    if (getIndex(x) < 0)
      elements.add(x); // auto-boxing
  }

  /**
   * @modifies <tt>this</tt>
   * @effects <pre>
   *   if x is not in this 
   *     do nothing 
   *   else 
   *     remove x from this, i.e. 
   *     this_post = this - {x}</pre>
   */
  @DOpt(type=OptType.MutatorRemove)
  public void remove(int x) {
    // simple: 
    // wrong implementation of the behaviour%
    // elements.remove(x);
    // use wrapper type Integer
    // elements.remove(Integer.valueOf(x));
    
    // alternative:
    int i = getIndex(x);
    if (i < 0)  // x not in this
      return;
    
    // short-form:
    // elements.remove(i);
    
    
    // change element at position ith (which is x)
    elements.set(i, elements.lastElement());
    // uses Vector.remove(int index)
    elements.remove(elements.size() - 1);
  }

  /**
   * @effects <pre>
   *  if x is in this 
   *    return true 
   *  else 
   *    return false</pre>
   */
  @DOpt(type=OptType.ObserverContains)
  public boolean isIn(int x) {
    return (getIndex(x) >= 0);
  }

  
  /**
   * @effects return the cardinality of <tt>this</tt>
   */
  @DOpt(type=OptType.ObserverSize)
  public int size() {
    return elements.size();
  }
  
  /**
   * @effects
   *  if this is not empty
   *    return array Integer[] of elements of this
   *  else 
   *    return null 
   */
  @DOpt(type=OptType.Observer)  
  public Integer[] getElements() {
    if (size() == 0)
      return null;
    else
      return elements.toArray(new Integer[size()]);
  }
  
  /**
   * @effects <pre>
   *  if this is empty 
   *    throw an IllegalStateException
   *  else 
   *    return an arbitrary element of this</pre>
   */
  public int choose() throws IllegalStateException {
    if (size() == 0)
      throw new IllegalStateException("IntSet.choose: set is empty");
    return (Integer)elements.lastElement();
  }

  /**
   * @effects <pre>
   *  if x is in this 
   *    return the index where x appears 
   *  else 
   *    return -1</pre>
   */
  private int getIndex(int x) {
    for (int i = 0; i < elements.size(); i++) {
      if (x == elements.get(i))
        return i;
    }

    return -1;
  }
  
  @Override
  public String toString() {
    if (size() == 0)
      return "IntSet:{ }";
    
    // todo: better to use StringBuilder
    String s = "IntSet:{" + elements.elementAt(0);
    for (int i = 1; i < size(); i++) {
      s = s + " , " + elements.elementAt(i);
    }

    return s + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof IntSet))
      return false;

    // use Vector.equals to compare elements
    return elements.equals(((IntSet)o).elements);
  }
  
  /**
   * @effects <pre>
   *   if this satisfies abstract properties
   *     return true 
   *   else
   *     return false</pre>
   */
  public boolean repOK() {
    // why?
    if (elements == null)
      return false;

    for (int i = 0; i < elements.size(); i++) {
      Integer x = elements.get(i); 

      /* omitted due to the use of generic
      if (!(x instanceof Integer))
        return false;
      */

      for (int j = i + 1; j < elements.size(); j++) {
        if (elements.get(j).equals(x))
          return false;
      }
    }
    // all is ok
    
    return true;
  }
}
