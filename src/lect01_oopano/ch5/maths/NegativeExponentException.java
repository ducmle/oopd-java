package lect01_oopano.ch5.maths;

/**
 * Represents an exception that is thrown when a negative exponent is given.
 *  
 * 
 * @author dmle
 *
 */
public class NegativeExponentException extends RuntimeException {
  public NegativeExponentException(String msg) {
    super(msg);
  }
}
