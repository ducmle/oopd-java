package kengine;

import java.util.Vector;

/**
 * @overview Implements quick-sort algorithm.
 * 
 * @author dmle
 * 
 */
public class Sorting {

  /**
   * A method that implements quick-sort algorithm.
   * 
   * @param v
   *          a vector of integral values
   * @requires <code>v</code> is not <code>null</code>
   * @modifies <code>v</code>
   * @effects If the elements of <code>v</code> are not comparable throws
   *          <code>ClassCastException</code>; if some element of <code>v</code>
   *          is <code>null</code> throws <code>NullPointerException</code>;
   *          else sort <code>v</code> such that elements having larger index
   *          are less than those at smaller indexes. (i.e. in ascending order)
   * 
   */
  public static void quickSortAsc(Vector v) throws ClassCastException,
      NullPointerException {
    quickSort(v, 0, v.size() - 1);
  }

  /**
   * A method to sort the elements of a vector that are between the low and high
   * indexes.
   * 
   * @param v
   *          the vector to sort
   * @param low
   *          the left-most position
   * @param high
   *          the right most position
   * @requires <code>0 <= low</code> and <code>high < v.size()</code>
   * @modifies <code>v</code>
   * @effects sorts <code>v[low], ..., v[high]</code> into ascending order
   */
  private static void quickSortAsc(Vector v, int low, int high) {
    if (low >= high)
      return;

    int mid = partition(v, low, high);
    quickSort(v, low, mid);
    quickSort(v, mid + 1, high);
  }

  /**
   * A method that arranges elements of an array into two sub-arrays separated
   * by a middle value that is greater than all the elements of the first (left)
   * sub-array and is less than all the elements of the second (right)
   * sub-array.
   * 
   * @param v
   *          an array (here represented as a {@link Vector} to partition
   * @param i
   *          the left-most position of the array
   * @param j
   *          the righ-most position of the array
   * @requires <code>v</code> is not <code>null</code> and
   *           <code>0 <= i < j < v.size()</code>
   * @modifies <code>v</code>
   * @effects Reorders the elements into two contiguous groups,
   *          <code>v[i],...,v[res]</code> and <code>v[res+1],...,v[j]</code>,
   *          such that each element in the second group is at least as large as
   *          each element of the first group. Returns <code>res</code>.
   */
  private static int partitionAsc(Vector v, int i, int j) {
    Comparable x = (Comparable) v.get(i);
    while (true) {
      while (((Comparable) v.get(j)).compareTo(x) > 0)
        j--;
      while (((Comparable) v.get(i)).compareTo(x) < 0)
        i++;

      if (i < j) { // need to swap
        Object temp = v.get(i);
        v.setElementAt(v.get(j), i);
        v.setElementAt(temp, j);
        j--;
        i++;
      } else {
        return j;
      }
    }
  }

  /**
   * A method that implements quick-sort algorithm (descending order).
   * 
   * @param v
   *          a vector of integral values
   * @requires <code>v</code> is not <code>null</code>
   * @modifies <code>v</code>
   * @effects If the elements of <code>v</code> are not comparable throws
   *          <code>ClassCastException</code>; if some element of <code>v</code>
   *          is <code>null</code> throws <code>NullPointerException</code>;
   *          else sort <code>v</code> such that elements having larger index
   *          are less than those at smaller indexes. (i.e. in descending order)
   * 
   */
  public static void quickSort(Vector v) throws ClassCastException,
      NullPointerException {
    quickSort(v, 0, v.size() - 1);
  }

  /**
   * A method to sort the elements of a vector that are between the low and high
   * indexes.
   * 
   * @param v
   *          the vector to sort
   * @param low
   *          the left-most position
   * @param high
   *          the right most position
   * @requires <code>0 <= low</code> and <code>high < v.size()</code>
   * @modifies <code>v</code>
   * @effects sorts <code>v[low], ..., v[high]</code> into descending order
   */
  private static void quickSort(Vector v, int low, int high) {
    if (low >= high)
      return;

    int mid = partition(v, low, high);
    quickSort(v, low, mid);
    quickSort(v, mid + 1, high);
  }

  /**
   * A method that arranges elements of an array into two sub-arrays separated
   * by a middle value that is greater than all the elements of the first (left)
   * sub-array and is greater than all the elements of the second (right)
   * sub-array.
   * 
   * @param v
   *          an array (here represented as a {@link Vector} to partition
   * @param i
   *          the left-most position of the array
   * @param j
   *          the righ-most position of the array
   * @requires <code>v</code> is not <code>null</code> and
   *           <code>0 <= i < j < v.size()</code>
   * @modifies <code>v</code>
   * @effects Reorders the elements into two contiguous groups,
   *          <code>v[i],...,v[res]</code> and <code>v[res+1],...,v[j]</code>,
   *          such that each element in the first group is at least as large as
   *          each element of the second group. Returns <code>res</code>.
   */
  private static int partition(Vector v, int i, int j) {
    Comparable x = (Comparable) v.get(i);
    while (true) {
      while (((Comparable) v.get(j)).compareTo(x) < 0)
        j--;
      while (((Comparable) v.get(i)).compareTo(x) > 0)
        i++;

      if (i < j) { // need to swap
        Object temp = v.get(i);
        v.setElementAt(v.get(j), i);
        v.setElementAt(temp, j);
        j--;
        i++;
      } else {
        return j;
      }
    }
  }
}
