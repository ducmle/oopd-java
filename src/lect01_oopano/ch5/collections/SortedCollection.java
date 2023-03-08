package lect01_oopano.ch5.collections;

import java.util.Vector;
import utils.SortOrder;
import utils.Sorted;
import utils.DomainConstraint;
import utils.collections.Collection;

/**
 * @overview 
 *  {@link SortedCollection} are collections whose elements are sorted in a pre-defined order.
 *  
 * @author dmle
 *
 * @version 2017 
 */
public class SortedCollection<E> implements Collection<E> {
  @DomainConstraint(type="elements",mutable=true,optional=false)
  @Sorted(order=SortOrder.Asc)
  private Vector<E> elements;
  
  // rest of class body
}
