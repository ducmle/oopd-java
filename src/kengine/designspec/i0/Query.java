package kengine.designspec.i0;

/**
 * @overview 
 *  Provides information about the keywords of a query and the documents 
 *  that match those keywords. 
 *  Documents are accessed using indexes between 0 and size. 
 *  Documents are ordered by the number of matches they contain, 
 *  with document 0th containing the most matches
 *  
 * @author dmle
 *  
 * @version (iteration) 0
 */
class Query {

  /**
   * @effects returns the keywords of this 
   */
  String[] keys()

  /**
   * @effects returns a count of the documents that match the query
   */
  int size()
  
  /**
   * @effects 
   *  If 0 <= i < size 
   *    returns the ith matching document
   *  else
   *    throws IndexOutOfBoundException
   */
  Doc fetch (int i) throws IndexOutOfBoundException
} // end Query
