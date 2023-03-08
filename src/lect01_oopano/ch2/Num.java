package lect01_oopano.ch2;


/**
 * A class to demonstrate class and procedures (methods) in Java.
 * 
 * @author dmle
 * 
 */
public class Num {
  /** a class providing useful numeric routines */
  
  public static float sqrt(float coef) {
    // REQUIRE: coef > 0
    // EFFECTS: Returns an approximation to the square root of coef
    float ans = coef / 2.0F;
    int i = 1;
    while (i < 7) {
      ans = ans - ((ans * ans - coef)/(2*ans));
      i = i + 1;
    }
    
    return ans;
  }
  
  /**
   * REQUIRES: <code>n</code> and <code>d</code> to be greater than 0 the gcd is
   * computed by repeated subtraction.<br>
   * 
   * The Euclidean algorithm for two natural numbers:<br>
   * subtract d from n to find the remainder r (which is less than d), repeat
   * this process for d and r to find r', and so on until the remainder is zero.
   * This happens because the sequence r, r', ... is strictly decreasing. The
   * final divisor is the gcd value.
   */
  public static int gcd(int n, int d) {

    while (n != d) {
      if (n > d)
        n = n - d;
      else
        d = d - n;
    }

    return n;
  }
  
  public static int fact(int n) {
    // REQUIRES: n > 0
    int r = 1;
    while (n > 0) {
      r = r * n;
      n--;
    }
    
    return r;
  }
}
