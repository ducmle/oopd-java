package utils;

/**
 * @overview Represents exceptions that are thrown when there are duplicate
 *           elements found in a collection that does not allow such condition 
 *           to occur. 
 *            
 * @author dmle
 *
 */
public class DuplicateException extends RuntimeException {
  public DuplicateException(String msg) {
    super(msg);
  }
}
