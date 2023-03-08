package kengine.designspec.i2;

import utils.DomainConstraint;

/**
 * @overview Keeps track of both interesting and uninteresting words.
 *  Uninteresting words are obtained from a private file. 
 *  Records the number of times each interesting word occurs in each document.
 *
 * @author dmle
 *
 * @version (iteration) 2.0
 * - added rep, 
 * - added and improved the operations
 */
class WordTable {
  @DomainConstraint(type="Hashtable",optional=false)
  private Hashtable table;
  
  /**
   * @effects 
   *  If uninteresting-word file cannot be read 
   *    throws NotPossibleException 
   *  else initialises this to contain all words in the file
   */
  WordTable() throws NotPossibleException
  
  /**
   * @effects 
   *  If w is null or a nonword or an uninteresting word 
   *    returns false 
   *  else returns true
   */
  boolean isInteresting(String w)
  
  /**
   * @effects
   *  if w is in this 
   *    return a Vector of documents containing w
   *  else
   *    return null
   * @version 2.0
   */
  Vector lookUp(String w)
  
  /**
   * @requires d is not null
   * @modifies this
   * @effects adds to this interesting words of d with their numbers of occurrences
   * @version 2.0 
   */
  Hashtable addDoc(Doc d)
} // end WordTable
