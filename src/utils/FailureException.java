package utils;

/**
 * @overview An exception that is thrown when an unexpected system failures
 *           occur.
 * 
 * @author dmle
 * 
 */
public class FailureException extends RuntimeException {
  public FailureException(String msg) {
    super(msg);
  }
}
