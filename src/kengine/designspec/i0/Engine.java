package kengine.designspec.i0;

/**
 * @overview ...(omitted)...
 * @version (iteration) 0
 */
class Engine {

  /**
   * @effects 
   *  If the uninteresting words not retrievable
   *    throws NotPossibleException 
   *  else 
   *    creates NonKeyword and initialises the app. state appropriately
   */
  Engine() throws NotPossibleException

  /**
   * @effects 
   *  If WORD(w) = false or w in NonKeyword 
   *    throws NotPossibleException
   *  else 
   *    sets Keyword = {w}, performs the new query, and returns the result 
   */
  Query queryFirst(String w) throws NotPossibleException

  /**
   * @effects 
   *  If WORD(w) = false or w in NonKeyword or Key = {} or w in Keyword 
   *    throws NotPossibleException 
   *  else 
   *    add w to Keyword and returns the query result 
   */
  Query queryMore(String w) throws NotPossibleException
  
  /**
   * @effects 
   *  If t not in Title throws NotPossibleException
   *  else returns the document with title t 
   */
  Doc findDoc (String t) throws NotPossibleException
    
  /**
   * 
   * @effects 
   *  If u is not a URL for a site containing documents or u in URL 
   *    throws NotPossibleException 
   *  else adds the new documents to Doc.
   *    If no query was in progress 
   *      returns the empty query result 
   *    else
   *      returns the query result that includes any matching new documents
   */
  Query addDocs(String u) throws NotPossibleException
} // end Engine
