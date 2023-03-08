package kengine.designspec.i1;

/**
 * @overview ...(omitted)...
 * @author dmle
 * 
 * @version (iteration) 1.0
 */
class Query {
  /**
   * @effects returns an empty query
   */
  Query()

  /**
   * @requires wt and w are not null
   * @effects initialises this to contain w
   */
  Query(WordTable wt, String w)
    
  /**
   * @requires w is not null
   * @modifies this
   * @effects 
   *  If this is empty or w is already a keyword in this
   *    throws NotPossibleException 
   *  else modifies this to contain w and all keywords already in this 
   */
  void addKey(String w) throws NotPossibleException
  
  /**
   * @requires d is not null
   * @modifies this
   * @effects 
   *  If this is not empty and d contains all the keywords of this 
   *    adds d to this as a query result 
   *  else do nothing
   */  
  void addDoc(Doc d)
  
  ///// END version 1.0
  
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
