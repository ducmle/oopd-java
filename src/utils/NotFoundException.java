package utils;

/**
 * @overview Represents an exception that is thrown when some item is not found 
 *           in a given collection. 
 *           
 * @author dmle
 *
 */
public class NotFoundException extends RuntimeException {
  public NotFoundException(String msg) {
    super(msg);
  }
}
