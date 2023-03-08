package kengine.designspec.i1;

/**
 * @overview Keeps track of both interesting and uninteresting words.
 *  Uninteresting words are obtained from a private file. 
 *  Records the number of times each interesting word occurs in each document.
 *
 * @author dmle
 *
 * @version (iteration) 1.0
 */
class WordTable {
  
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
   * @requires d is not null
   * @modifies this
   * @effects adds to this interesting words of d with their numbers of occurrences 
   */
  void addDoc(Doc d)
} // end WordTable
