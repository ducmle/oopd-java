package lect02_th.vehiclesintf;

/**
 * @overview 
 *  Represents objects that have some priority over others. 
 *  Priority is determined based on the object dimension (width, length, height).
 */
public interface PriorityObject<T> {

  /**
   * @effects <pre>
   *            if pv is null 
   *              return a positive number
   *            else
   *              if the total weight OR dimension of this is bigger than pv (
   *              dimension = width*length*height)
   *                return a positive number
   *              else if total weight and dimension of this are equal to pv
   *                return 0
   *              else 
   *                return a negative number
   *          </pre>
   */
  public int comparePriorityTo(T pv);
}
