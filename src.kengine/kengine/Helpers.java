package kengine;

import utils.NotPossibleException;

/**
 * @overview A general helper class responsible for performing procedures that are 
 *           needed by a number of application classes.
 *           At present only <code>canon</code> is defined.
 * @version  2.0
 *              
 * @see      "Program development in Java", pgs 332-333,365
 * @author dmle
 *
 */
public class Helpers {
  
  /**
   * A method to produces a canonical form of a given string. 
   * 
   * @param s a <code>String</code> from which a canonical form is to be generated
   * @effects If <code>s</code> is <code>null</code> throws <code>NotPossibleException</code>
   *          else returns a canonical form of <code>s</code>.
   * @version 2.0 uses the lower-case of <code>s</code> as the canonical form of <code>s</code> 
   */
  public static String canon(String s) throws NotPossibleException {    
    return s.toLowerCase();
  }
}
