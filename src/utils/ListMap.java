/**
 * A helper class to store a Map in which keys are stored in the order 
 * that they were added to the map. 
 * @author Duc M Le  <a href="mailto:dmle@doc.ic.ac.uk"><i>dmle@doc.ic.ac.uk</i></a>
 * @version 1.0
 * Department of Computing, Imperial College
 */
package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ListMap extends HashMap {
  private String name;
  private List keys;
  private List valList;

  /**
   * The allowed NULL key
   */
  // public static final Object NULL_KEY = null;

  public ListMap() {
    this(null);
  }

  public ListMap(String name) {
    super();
    keys = new ArrayList();
    valList = new ArrayList();
    this.name = name;
  }

  /**
   * Insert entry into this map, overriding existing entry if one with the same
   * key already exists.
   */
  public Object put(Object key, Object value) {
    int index = keys.indexOf(key);
    if (index > -1) {
      // overrides
      keys.set(index, key);
      valList.set(index, value);
    } else {
      keys.add(key);
      valList.add(value);
    }
    return super.put(key, value);
  }

  // /**
  // * Create some null-valued entries with the provided keys
  // * @param key
  // * @return
  // */
  // public void putKeys(Collection keys) {
  // for (Iterator it = keys.iterator(); it.hasNext();) {
  // this.put(it.next(), null);
  // }
  // }

  public Object remove(Object key) {
    if (keys != null) {
      int i = keys.indexOf(key);
      if (i > -1)
        return valList.remove(i);
    }

    return null;
  }

  public List keyList() {
    return keys;
  }

  public Collection values() {
    return valList;
  }

  /**
   * Remove the first entry
   * 
   * @return
   */
  public Object removeFirst() {
    if (!keys.isEmpty()) {
      Object fk = keys.remove(0);
      Object o = remove(fk);
      return o;
    }

    return null;
  }

  /**
   * Remove the last entry
   * 
   * @return
   */
  public Object removeLast() {
    if (!keys.isEmpty()) {
      Object fk = keys.remove(keys.size() - 1);
      Object o = remove(fk);
      return o;
    }

    return null;
  }

  /**
   * Add a (key,value) pair to an existing list of the map. If there is already
   * a value of this key in the map, then create a collection object to hold
   * this and the new value.
   * 
   * @param key
   * @param value
   */
  public Object put(final Object key, final Object value,
      boolean duplicateAllowed) {
    Object result = null;
    if (!duplicateAllowed) {
      result = put(key, value);
    } else {
      result = get(key);
      if (result == null || (!(result instanceof Collection))) {
        Collection c = new ArrayList();
        if (result != null)
          c.add(result);
        c.add(value);
        put(key, c);
        result = c;
      } else {
        ((Collection) result).add(value);
      }
    }

    return result;
  }

  public void clear() {
    keys.clear();
    valList.clear();
    super.clear();
  }

  public String getName() {
    return name;
  }
}
