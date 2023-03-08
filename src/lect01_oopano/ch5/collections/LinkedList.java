package lect01_oopano.ch5.collections;

import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.OptType;
import utils.collections.Collection;

/**
 * @overview Represents a (possibly empty) list of elements of arbitrary types.
 * 
 * @attributes 
 *   cell    Cell
 * 
 * @object
 *   A typical list is c = (x1,...,xn), where cell.head(x1) and cell.tail(x2,...,xn) are elements.
 *  
 *   <p>Recursive definition:
 *   Basis
 *    () is a list
 *   Induction
 *    for all object x and for all list L, a new list is formed by inserting x 
 *    into the first position of L to become a head, thereby making the 
 *    existing elements of L become the tail of the new list.
 * 
 * @abstract_properties
 *  mutable(cell)=true /\ optional(cell)=true /\ 
 *  cell.tail = (x1,...,xk) is a sequence /\ (cell.head,x1,...,xk) is a sequence
 *      
 * @author dmle
 * 
 * @version 
 * 2017: improved to become generic and to implement {@link Collection}.  
 */
public class LinkedList<E> implements Collection<E> {
  @DomainConstraint(type="Cell",mutable=true,optional=true)
  private Cell<E> cell;
  
  // derived attribute
  @DomainConstraint(type="Integer",mutable=false,optional=true)
  private int sz;
  
  // constructors
  /**
   * @effects initialises this to be ()
   */
  public LinkedList() {
    // empty list
  }

  /**
   * @requires n != null
   * @effects initialises this to be a list (n.head) 
   */
  private LinkedList(Cell<E> n) {
    cell = n;
  }
  
  /**
   * @requires x != null
   * @effects initialises this to be (x)
   */
  public LinkedList(E x) {
    cell = new Cell<>(x);
    sz++;
  }

  /**
   * @requires x != null
   * @modifies this
   * @effects 
   *  if this is empty
   *   initialise cell such that cell.head = x
   *  else
   *   inserts x into the first position of this (pushing
   *   the element at the current position to the right)
   */
  public void push(E x) {
    if (cell == null) {
      cell = new Cell<>(x);
    } else {
      // push x
      cell = new Cell<>(x, cell);
    }
    sz++;
  }

  /**
   * @requires x != null
   * @modifies this
   * @effects 
   *  if this is empty
   *   initialise cell such that cell.head = x
   *  else
   *   adds x to the end of this
   */
  @DOpt(type=OptType.MutatorAdd)
  public void add(E x) {
    if (cell == null) {
      cell = new Cell<>(x);
    } else {
      // add x to the tail of the last cell
      Cell<E> tail = cell.getTail(), last = null;
      while (tail != null) {
        last = tail;
        tail = last.getTail();
      }
      
      if (last == null) { // cell is the last one
        cell.setTail(new Cell<>(x));
      } else {
        last.setTail(new Cell<>(x));
      }
    }
    sz++;
  }
  
  /**
   * @requires x != null
   * @modifies this
   * 
   * @effects
   *   if this is empty or x is not in this
   *    do nothing
   *   else
   *    remove the first occurrence of x from this
   */
  @DOpt(type=OptType.MutatorRemove)
  public void remove(E x) {
    boolean found = false;
    Cell n = cell;
    Cell prev = null;
    while (!found && n != null) {
      Cell tail = n.tail;
      if (n.head.equals(x)) { // compare using equals
        if (prev != null)
          prev.tail = tail;
        else
          cell = tail;
        found = true;
        sz--;
      } else {
        prev = n;
        n = tail;
      }
    }
  }

  /**
   * @requires x != null
   * @effects 
   *   if x is in this
   *     return true
   *   else
   *     return false
   */
  @DOpt(type=OptType.ObserverContains)
  public boolean lookUp(E x) {
    boolean found = false;
    Cell n = cell;
    while (!found && n != null) {
      if (n.head.equals(x)) { // compare using equals
        found = true;
      }
      n = n.tail;
    }

    return found;
  }

  /**
   * @effects
   *  return head
   */
  @DOpt(type=OptType.Observer) @AttrRef("head")
  public E head() {
    if (cell != null)
      return cell.head;
    else
      return null;
  }
  
  /**
   * @effects
   *  if this is empty or a single-element list
   *    return null
   *  else 
   *    return tail as a new list
   */
  @DOpt(type=OptType.Observer) @AttrRef("tail")
  public LinkedList<E> tail() {
    if (cell != null) { /* this is not empty */
      Cell tail = cell.tail;
      if (tail != null) {
        // return as a new list
        Cell ctail = tail.clone();
        LinkedList l = new LinkedList(ctail);
        return l;
      } else {
        return null;
      }
    } else {
      return null;
    }
  }

  /**
   * @requires index >= 0
   * @effects
   *    if this is empty or index is out of range
   *      return null
   *    else
   *      return the element at the position index
   */
  @DOpt(type=OptType.Observer)
  public E get(int index) {
    if (cell == null) {
      return null;
    } else {
      int count = 0;
      Cell<E> n = cell;
      while (n != null) {
        if (count == index) {
          return n.head;
        } else {
          count++;
          n = n.tail;
        }
      }
      
      return null;
    }
  }
  
  /**
   * @effects 
   *  returns the number of occurrences of the elements of this
   */
  @DOpt(type=OptType.ObserverSize)
  public int size() {
    return sz;
  }
  
  /**
   * @effects 
   *  if this is empty
   *    return true
   *  else 
   *    return false
   */
  @DOpt(type=OptType.Observer)
  public boolean isEmpty() {
    if (cell == null) // empty
      return true;
    else
      return false;
  }
  
  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    if (cell == null) { // empty 
      sb.append("()");
    } else {
      sb.append("(");
      Cell n = cell;
      while (n != null) {
        sb.append(n.head);
        n = n.tail;
        if (n != null)
          sb.append(",");
      }
      sb.append(")");
    }

    return sb.toString();
  }

  /**
   * @overview Represents a linked cell that contains a head value of the current list and a nullable
   *           reference pointer to another cell representing the tail of the current list.
   * 
   * @attributes
   *   head    E 
   *   tail    Cell
   *  
   * @object
   *   A typical cell is <v,r>, where head(v) and tail(r).
   * 
   * @abstract_properties
   *  mutable(head)=false /\ optional(head)=false /\ 
   *  mutable(tail)=false /\ optional(tail)=true
   * 
   * @rep_invariant
   *  head != null
   *  
   * @author dmle
   */
  private class Cell<T> {
    private T head;
    private Cell<T> tail;

    // constructors
    /**
     * @requires v != null
     * @effects 
     *  initialise this to be <v,r>
     */
    public Cell(T v, Cell<T> r) {
      head = v;
      tail = r;
    }

    /**
     * @requires v != null
     * @effects 
     *  initialise this to be <v,null>
     */
    public Cell(T v) {
      this(v, null);
    }
    
    /**
     * @effects 
     *  sets this.{@link #tail} = tail 
     */
    public void setTail(Cell<T> tail) {
      this.tail = tail;
    }

    /**
     * @effects
     *  return {@link #tail} 
     */
    public Cell<T> getTail() {
      return tail;
    }
    
    /**
     * @effects
     *  return a deep copy of this
     */    
    @Override
    public Cell<T> clone() {
      // deep clone
      T v = head; 
      
      // if ref is assigned, clone it as well
      Cell<T> r = (tail != null) ? tail.clone() : null;
      
      Cell<T> n = new Cell(v,r);
      return n;
    }
    
    /**
     * @effects
     *  if this satisfies rep invariant
     *    return true
     *  else
     *    return false
     */
    public boolean repOK() {
      if (head == null)
        return false;
      
      return true;
    }
  } // end Cell
} /** end {@link LinkedList} */