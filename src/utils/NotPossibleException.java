package utils;

/**
 * OVERVIEW: a run-time exception that is (not expected to be) thrown 
 * by a method that could not perform its operation due to input 
 * data errors.
 * 
 * @author dmle
 *
 */
public class NotPossibleException extends RuntimeException {
  public NotPossibleException(String s) {
    super(s);
  }
}
