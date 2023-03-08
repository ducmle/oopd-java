package kengine.designspec.i1;

/**
 * @overview 
 *  Represents the communication module responsible for obtaining documents
 *  from remote sites.
 *  
 * @author dmle
 *
 * @version (iteration) 1.0
 */
public class Comm {
  /**
   * @effects 
   *  If u isn't a valid URL or the site it names fails to respond 
   *    throws NotPossibleException 
   *  else returns a generator for documents from site u (as strings)
   */
  static Iterator getDocs (String u) throws NotPossibleException
} // end Comm
