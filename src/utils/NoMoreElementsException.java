package utils;

/**
 * @overview <code>NoMoreElementException</code> is thrown by code using collections 
 *            when there are no elements in the collection that are available 
 *            for processing. 
 *           
 * @author dmle
 *
 */
public class NoMoreElementsException extends RuntimeException {
  public NoMoreElementsException(String msg) {
    super(msg);
  }
}
