package kengine.reqspec;

/**
  @overview 
   Represents keyword search engines. An engine holds a mutable collection of documents, which 
   are obtained from some given URLs. The engine is able to pocess a keyword query to 
   search for documents that contain the keywords.
   
   The matching documents are ranked based on the frequencies of the keywords found in them.
   
   The engine has a private file that contains the list of uninteresting words.
 */
class KEngine {
  
  /**
    @effects 
     Starts the engine running with NonKeyword  
       containing the words in the private file.
     All other sets are empty.
   */
  static startEngine()
  
  /**
    @checks u does not name a site in URL and 
       u names a site that provides documents
    
    @effects 
       Adds u to URL and 
       adds the documents at site u with new titles to Document. 
       If Keyword is non-empty adds any documents that match
         the keywords to Match.
   */
  addDocuments(String u) 
  
  /**
    @checks: w is not in NonKeyword
    
    @effects 
     Sets Keyword = {w} and
     makes Match contain the documents that match w,
       ordered as required. 
   */
  query(String w)
  
  /**
    @checks Key != {} and w not in NonKeyword and w not in Keyword
    
    @effects 
     Adds w to Keyword and 
     makes Match be the documents already
       in Match that additionally match w. 
     Orders Match properly.
    
   */
  queryMore(String w)
  
  /**
    @checks t is in titles
    
    @effects 
     return d in Document s.t. d's title = t
   */
  findDoc(String t)
} // end Engine