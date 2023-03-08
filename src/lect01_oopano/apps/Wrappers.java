package lect01_oopano.apps;

/**
 * @overview A program that creates and manipulate objects of wrapper classes.
 *  
 * @author dmle
 *
 */
public class Wrappers {
  /**
   * The run method
   */
  public static void main(String[] args) {
    // create using constructor
    Integer i = new Integer(10);      /* i = 10 */
        
    // create object using parse operation
    i = Integer.parseInt("10");       /* i = 10 */
    
    // create object using auto-boxing
    i = 5;                            /* i = 5 */
    
    // unboxing in expression
    int e = i + 10;                   /* e = 15 */
    
    // convert back to primitive using intValue
    int p = i.intValue();             /* p = 5 */
    
    // convert using unboxing
    p = i;                            /* p = 5 */
  }
}
