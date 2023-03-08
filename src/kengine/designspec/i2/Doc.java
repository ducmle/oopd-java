package kengine.designspec.i2;

/**
 * @overview ...(omitted)...
 *  
 * @author dmle
 *  
 * @version (iteration) 2.0
 * - added words()
 */
class Doc {
  /**
   * @effects 
   *  If d cannot be processed as a document 
   *    throws NotPossibleException
   *  else makes this be the Doc corresponding to d  
   */
  Doc(String d) throws NotPossibleException
  
  ///// END version 1.0
  
  /**
   * @effects returns the title of this 
   */
  String title()

  /**
   * @effects returns the body of this
   */
  String body()
  
  
  /**
   * @effects 
   *  return all words of this in sequence
   * @version 2.0
   */
  Iterator words()
} // end Doc
