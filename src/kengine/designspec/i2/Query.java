package kengine.designspec.i2;

import utils.DomainConstraint;

/**
 * @overview ...(omitted)...
 * @author dmle
 * 
 * @version (iteration) 2.0 
 * - added rep, 
 * - added implementation sketches for some methods
 */
class Query {
  @DomainConstraint(type="WordTable",optional=false)
  private WordTable k;
  @DomainConstraint(type="String[]",optional=false)
  private String[] keys;
  @DomainConstraint(type="Vector")
  private Vector matches;
  
  /**
   * @effects returns an empty query
   */
  Query()

  /**
   * @requires wt and w are not null
   * @effects initialises this to contain w
   * 
   * @pseudocode <pre>--- implementation sketch -----
       lookup the key in the WordTable
       sort the matches using quickSort</pre>
   */
  Query(WordTable wt, String w)
    
  /**
   * @requires w is not null
   * @modifies this
   * @effects 
   *  If this is empty or w is already a keyword in this
   *    throws NotPossibleException 
   *  else modifies this to contain w and all keywords already in this
   *
   * @pseudocode <pre>--- implementation sketch -----
       lookup the new key in the WordTable
       store the information about the matches in a hash table
       for each current match, look up the document in the hash
         table and if it is there, store it in a vector
       sort the vector using quickSort </pre>
   */
  void addKey(String w) throws NotPossibleException
  
  /**
   * @requires d is not null
   * @modifies this
   * @effects 
   *  If this is not empty and d contains all the keywords of this 
   *    adds d to this as a query result 
   *  else do nothing
   *  
   * @pseudocode <pre>--- implementation sketch -----
       use the argument table to get the number of occurrences 
         of each current key
       if the document has all the keywords, compute the sum
         and insert the (doc,sum) pair in the vector of matches
       </pre>
   *
   * @version 2.0
   */  
  void addDoc(Hashtable t, Doc d)
  
  ///// END version 1.0
  ///// END version 2.0
  
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
