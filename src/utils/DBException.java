package utils;

/**
 * @overview An exception that is thrown when an error occured with database connection 
 *           or processing
 * 
 * @author dmle
 * 
 */
public class DBException extends RuntimeException {
  public DBException(String msg) {
    super(msg);
  }
}
