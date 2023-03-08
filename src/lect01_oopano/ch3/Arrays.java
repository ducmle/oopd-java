package lect01_oopano.ch3;


/**
 * @overview This class provides a number of standalone procedures useful for 
 *           manipulating arrays of ints
 * @author dmle
 *
 */
public class Arrays {

  /**
   * @effects <pre>
   *          if x is in a
   *            returns an index where x is stored
   *          else 
   *            returns -1</pre>
   */
  public static int search(int[] a, int x) {
    for (int i = 0; i < a.length; i++) {
      if (a[i] == x) return i;
    }
    
    return -1;
  }

  /**
   * A method to search for the index of a given element of a sorted array.
   * 
   * @requires <tt>a</tt> is sorted in ascending order
   * @effects <pre>
   *            if x is in a 
   *              returns an index where x is stored
   *            else 
   *              returns -1</pre>
   */
  public static int searchSorted(int[] a, int x) {
    // use sequential search
    for (int i = 0; i < a.length; i++) {
      if (a[i] == x) 
        return i;
      else if (a[i] > x) {
        return -1;
      }
    }
    
    return -1; // x is greater than all elements of a    
  }
  
  /**
   * A method to search for the index of a given element of a sorted array. 
   * 
   * @requires <tt>a</tt> is sorted in ascending order
   * @effects <pre>
   *            if x is in a 
   *              returns an index where x is stored
   *            else 
   *              returns -1</pre>
   */
  public static int searchSortedBin(int[] a, int x) {
    // use binary search
    if (a == null) return -1;
    int low = 0; 
    int high = a.length - 1;
    while (low <= high) {
      int mid = (low + high)/2; // the floor mid point
      if (x == a[mid]) return mid;
      if (x < a[mid])
        high = mid - 1;
      else
        low = mid + 1;        
    }
    
    return -1; // x is greater than all elements of a    
  }

  /**
   * A method to sort the elements of an array in ascending order.
   * 
   * @effects rearranges the elements of <tt>a</tt> into ascending order,
   *          e.g. <br>
   *          <pre>
   *            if a = [3,1,6,1] before 
   *              the call, on return 
   *              a_post = [1,1,3,6]</pre>
   * @modifies <tt>a</tt>              
   * */
  public static void sort(int[] a) {
    // quick-sort implementation
    if (a == null) return;
    quickSort(a, 0, a.length - 1);
  }

  /**
   * A method to sort the elements of a vector that are between the low and high
   * indexes.
   * 
   * @requires <tt>0 <= low</tt> and <tt>high < a.length</tt>
   * @modifies <tt>a</tt>
   * @effects sorts <tt>a[low], ..., a[high]</tt> into ascending order
   */
  public static void quickSort(int[] a, int low, int high) {
    if (low >= high)
      return;

    int mid = partition(a, low, high);
    quickSort(a, low, mid);
    quickSort(a, mid + 1, high);
  }

  /**
   * A method that arranges elements of an array into two sub-arrays separated
   * by a middle value that is greater than all the elements of the first (left)
   * sub-array and is less than all the elements of the second (right)
   * sub-array.
   * 
   * @requires <tt>a</tt> is not <tt>null</tt> and
   *           <tt>0 <= i < j < a.length</tt>
   * @modifies <tt>a</tt>
   * @effects Reorders the elements into two contiguous groups,
   *          <tt>a[i],...,a[res]</tt> and <tt>a[res+1],...,a[j]</tt>,
   *          such that each element in the second group is at least as large as
   *          each element of the first group. Returns <tt>res</tt>.
   */
  public static int partition(int[] a, int i, int j) {
    int x = a[i];
    while (true) {
      while (a[j] > x)
        j--;
      while (a[i] < x)
        i++;

      if (i < j) { // need to swap
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        j--;
        i++;
      } else {
        return j;
      }
    }
  }
}
