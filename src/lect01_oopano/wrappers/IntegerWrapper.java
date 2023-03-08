package lect01_oopano.wrappers;

/**
 * @overview A program that creates and manipulate Integer objects.
 *  
 * @author dmle
 */
public class IntegerWrapper {
  /**
   * The run method
   */
  public static void main(String[] args) {
    Integer i;
    int j, k;
        
    // create object using auto-boxing
    i = 5;                            /* i = Integer(5) */

    // auto-convert to primitive using unboxing
    k = i;                            /* k = 5 */
    
    // unboxing i back to primitive in expression
    j = i + 10;                       /* j = 15 */
    
    System.out.printf("i, j, k = %d, %d, %d %n", i, j, k);
  }
}
