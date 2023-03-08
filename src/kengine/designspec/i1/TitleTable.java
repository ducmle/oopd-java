package kengine.designspec.i1;

/**
 * @overview 
 *  Keeps track of documents and their titles. 
 *  
 * @author dmle
 *
 * @version (iteration) 1.0
 */
class TitleTable {

  /**
   * @effects Initialises this to be empty
   */
  TitleTable()
  
  /**
   * @requires d is not null
   * @modifies this
   * @effects 
   *  If a document with d's title already in this
   *    throws DuplicateException 
   *  else adds d with its title to this
   */
  void addDoc(Doc d) throws DuplicateException
  
  /**
   * @effects 
   *  If t is null or there is no document with title t in this 
   *    throws NotPossibleException 
   *  else returns the document with title t  
   */
  Doc lookUp(String t) throws NotPossibleException
} // end TitleTable
