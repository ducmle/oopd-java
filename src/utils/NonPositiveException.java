package utils;

public class NonPositiveException extends RuntimeException {
  public NonPositiveException(String msg) {
    super(msg);
  }
}
