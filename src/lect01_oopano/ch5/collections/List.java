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
 *      head    E 
 *      tail    List
 * 
 * @object
 *   A typical list is c = (x1,...,xn), where head(x1) and tail(x2,...,xn) are elements.
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
 *  mutable(head)=false /\ optional(head)=true /\ 
 *  mutable(tail)=true /\ optional(tail)=true /\
 *  tail = (x1,...,xk) is a sequence /\ (head,x1,...,xk) is a sequence /\ 
 *  tail is not empty -> head is initialised 
 * 
 * @author dmle
 * 
 * @version 
 * 2017: improved to become generic and to implement {@link Collection}. 
 */
public class List<E> implements Collection<E> {
  @DomainConstraint(type="E",mutable=false,optional=true)
  private E head;
  @DomainConstraint(type="List",mutable=true,optional=true)
  private List<E> tail;

  // derived attribute
  @DomainConstraint(type="Integer",mutable=false,optional=true)
  private int sz;
  
  /**
   * @effects initialises this to be ()
   */
  public List() {
    // empty list
  }

  /**
   * This constructor together with operation <tt>push</tt> implement the inductive rule.
   * 
   * @requires x != null
   * @effects initialises this to be (x)
   */
  private List(E x) {
    // make x the head
    head = x;
    
    // update length
    sz++;
  }

  /**
   * This constructor is used to clone a list. 
   * 
   * @requires h != null
   * @effects  initialise this to be (h,x1,...,xn) where t = (x1,...,xn) 
   */
  private List(E h, List t) {
    head = h;
    tail = t;
  }

  /**
   * This operation implements the inductive rule.
   * 
   * @requires x != null
   * @modifies this
   * @effects 
   *  if this is empty
   *    head = x
   *  else
   *    inserts x into the first position of this (pushing
   *    the element at the current position to the right)
   */
  public void push(E x) {
    if (head == null) {
      // make x the head
      head = x;
    } else {
      // push head into tail
      if (tail == null) {
        tail = new List(head);
      } else {
        tail.push(head);
      }
      
      // make x the head
      head = x;
    }
    
    // update length
    sz++;
  }

  /**
   * This operation implements the inductive rule.
   * 
   * @requires x != null
   * @modifies this
   * @effects 
   *  if this is empty
   *    head = x
   *  else
   *    adds x to the end of this
   */
  @DOpt(type=OptType.MutatorAdd)
  public void add(E x) {
    if (head == null) {
      // make x the head
      head = x;
    } else {
      // keep head the same, add x to tail
      if (tail == null) {
        tail = new List(x);
      } else {
        tail.add(x);
      }
    }
    
    // update length
    sz++;
  }
  
  /**
   * @requires x != null
   * @effects
   *   if this is empty or x is not in this
   *    do nothing
   *   else
   *    remove the first occurrence of x from this
   */
  @DOpt(type=OptType.MutatorRemove)
  public void remove(E x) {
    if (head == null)
      return;
    
    
    if (head.equals(x)) {
      // x is head
      if (tail != null) {
        // has tail: make tail.head the head
        head = tail.head;
        tail.remove(head);
      } else {
        // no tail: nullify head
        head = null;
      }
    } else if (tail != null) {
      // x is not head /\ has tail: recursively remove x in tail
      tail.remove(x);
    }
    
    // cut tail if empty
    if (tail != null && tail.isEmpty()) {
      tail = null;
    }
    
    // update length
    sz--;
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
    if (head == null)
      return false;
    
    if (head.equals(x)) {
      return true;
    } else if (tail != null) {
      return tail.lookUp(x);
    } else {
      return false;
    }
  }

  /**
   * @effects
   *  return head
   */
  @DOpt(type=OptType.Observer) @AttrRef("head")
  public E head() {
    return head;
  }

  /**
   * @effects
   *  if this is empty or a single-element list
   *    return null
   *  else 
   *    return tail as a new list
   */
  @DOpt(type=OptType.Observer) @AttrRef("tail")
  public List<E> tail() {
    if (tail == null) {
      return null;
    } else {
      return tail.clone();
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
    E h = head;
    List<E> t = tail;

    int count = 0;
    while (count < index) {
      if (t == null) { // out of range
        return null;
      } else {
        h = t.head;
        t = t.tail;
        count++;
      }
    }
    
    return h;
  }

  /**
   * @effects 
   *  return the number of occurrences of the elements of this
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
    if (head == null) // empty
      return true;
    else
      return false;
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    if (head == null) { // empty 
      sb.append("()");
    } else {
      sb.append("(");
      E h = head;
      List t = tail;
      sb.append(h);
      while (t != null) {
        sb.append(",");
        sb.append(t.head);
        t = t.tail;
      }
      sb.append(")");
    }

    return sb.toString();
  }
  
  /**
   * @effects
   *  return a copy of this
   */
  @Override
  public List<E> clone() {
    if (head == null)
      return null;
    
    E h = head;
    List<E> t;
    if (tail != null) {
      t = tail.clone();
    } else {
      t = null;
    }
    
    return new List<>(h,t);
  }
  
  /**
   * @effects
   *  if this satisfies rep invariant
   *    return true
   *  else
   *    return false
   */
  public boolean repOK() {
    if (tail != null && head == null)
      return false;
    
    return true;
  }
} /** end {@link List} */