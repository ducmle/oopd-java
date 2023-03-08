package kengine;

import java.util.Iterator;
import java.util.Vector;

import utils.NotImplementedException;
import utils.NotPossibleException;


/**
 * @overview A document contains a title and a text body
 * 
 * @see "Program Development in Java", pgs: 314,322,333
 * @version 1.0
 * @author dmle
 * 
 */
public class Doc {
  private String d; // the document content
  private String title; // the document title
  private String body; // the document body

  private Vector docWords; // the sequence of document words

  /**
   * Constructor method
   * 
   * @param d
   *          A string that contains the document content
   * @effects if d cannot be processed as a document throws
   *          <code>NotPossibleException</code> else makes <code>this</code> be
   *          the <code>Doc</code> corresponding to <code>d</code>
   * 
   */
  public Doc(String d) throws NotPossibleException {
    // check that d is an HTML file
    // stores this content for processing later
    this.d = d;
  }

  /**
   * A method to return the title of this document.
   * 
   * @effects returns the title of <code>this</code>
   */
  public String title() {
    // scans the document content once to extract its title
    // stores the title into the title attribute to use later
    if (title == null) {
      // note that we donot scan the entire body at this stage,
      // only up to the <title> tag
      int tind1 = d.indexOf("<title>");
      int tind2 = d.indexOf("</title>"); // must be well-formed
      if (tind1 < 0) {
        tind1 = d.indexOf("<TITLE>"); // possibly upper case
        tind2 = d.indexOf("</TITLE>");
      }

      if (tind1 >= 0 && tind2 >= 0) {
        // extract only the title text
        title = d.substring(tind1 + 7, tind2);
      }
    }

    return title;
  }

  /**
   * A method to return the body of this document.
   * 
   * @effects returns the body of <code>this</code>
   */
  public String body() {
    // scans the document content once to extract its body
    // stores the body into the body attribute to use later
    if (body == null) {
      // note that we donot scan the entire body at this stage,
      // only up to the <title> tag
      int bind1 = d.indexOf("<body");
      int bind2 = d.indexOf("</body>"); // must be well-formed
      if (bind1 < 0) {
        bind1 = d.indexOf("<BODY"); // possibly upper case
        bind2 = d.indexOf("</BODY>");
      }

      if (bind1 >= 0 && bind2 >= 0) {
        // we want to keep the <body</body> tag pairs in
        // the body text
        body = d.substring(bind1, bind2 + 7);
      }
    }

    return body;
  }

  /**
   * A method that is used to iterate over all the words in <code>this</code> in the order
   * that they appear. 
   * 
   * @effects returns a generator that will yield all the words in the document
   *          as strings in the order they appear in the text
   *          <p>
   * 
   *          This implementation also parses the Javascript text that is
   *          contained between the <code>&lt;script&gt;&lt;/script&gt;</code>
   *          tags.
   */
  public Iterator words() {
    // extracts body
    body();

    // scans the body to return only words (without tags)
    // assumes that Doc is immutable
    // note: recall that body has the enclosing <body></body> tag pairs
    if (docWords == null) {
      docWords = new Vector();
      final char OPEN_TAG = '<';
      final char CLOSED_TAG = '>';
      // final char FORWARD_SLASH = '/';
      final char NEW_LINE = '\n';
      final String[] SPECIALS = { NEW_LINE+"", "\t" };
      final boolean SKIP_SPECIAL_HTML = true;
      //final String[] JUNK_CHARS = { "`","``","''","(",")","-","[","]",":",";","\"",".",",","...","<",">"}; 
                 
      // reads the content one character at a time, stop each time
      // we get to a stop-word (e.g. space or a tag)
      char[] chars = body.toCharArray();
      char c;
      String w = null;
      String reading = null;
      boolean skip = false;
      for (int i = 0; i < chars.length; i++) {

        c = chars[i];

        // if we have found a new open tag
        if (!skip && c == OPEN_TAG) {
          // skip to locate the next closing and stop chars
          skip = true;
          reading = "";
          continue;
        }

        if (skip) {
          reading += c;
          // check closing and stop tags
          if (c == CLOSED_TAG) { // end of opening tag, beginning text
            // if told to skip special tags and reading
            // so far shows that it is some special tag then do nothing
            if (SKIP_SPECIAL_HTML) {
              // skip <script> and <style>
              if (!(reading.startsWith("script") || 
                  reading.startsWith("SCRIPT") ||
                  reading.startsWith("style") || 
                  reading.startsWith("STYLE")
                  )) {
                w = "";
              }
            } else {
              w = "";
            }
          } else if (c == OPEN_TAG) { // begin a new tag, end of text
            // process reading
            if (w != null) {
              w.trim();
              for (int j = 0; j < SPECIALS.length; j++) {
                if (w.startsWith(SPECIALS[j]))
                  w = w.substring(1); // remove special char at the beginning
                if (w.endsWith(SPECIALS[j]))
                  w = w.substring(0, w.length() - 1);// remove special char at
                                                     // the end
                
                // replace all remaining special chars by spaces
                w = w.replaceAll(SPECIALS[j], " ");
              }

              if (!w.equals("")) {
                String[] witems = w.split(" ");
                for (int j = 0; j < witems.length; j++) {
                  String witem = witems[j].trim();
                  if (!witem.equals("")) {
                    //for (int x = 0; x < JUNK_CHARS.length; x++) 
                    //  witem = witem.replaceAll(JUNK_CHARS[x], "");
                    
                    docWords.add(witem);
                  }
                }
              }
              // reset word
              w = null;
            }
            reading = null;
            // continue to parse from c
            skip = false; // stop skipping
            // decrement i so that we start from c from the next iteration
            i--;
          } else if (w != null) {
            // still inside a text
            // update word
            if (c != NEW_LINE)
              w += c;
          }
          // continue; // skip
        }
      }
    } // end body processing

    // create a generator from the body words
    // note: we could have used the built-in Vector.iterator() method here
    // instead.
    if (docWords != null)
      return new WordGenerator(docWords);
    else
      return null;
  }

  public String toString() {
    return title();
  }
  
  /**
   * @overview A generator implementation that is used by <code>Doc.words()</code> to 
   *           return a word iterator of a document.
   * 
   * @author dmle
   *
   */
  class WordGenerator implements Iterator {
    Vector words;
    int currWordIndex = -1;

    /**
     * 
     * @param words
     *          a word vector
     * @requires <code>words</code> must not be <code>null</code>
     */
    WordGenerator(Vector words) {
      this.words = words;
    }

    public boolean hasNext() {
      return (currWordIndex < words.size() - 1);
    }

    public Object next() {
      currWordIndex++;
      return words.get(currWordIndex);
    }

    /**
     * @effects throws
     *          <code>NotImplementedException<code> because <code>Doc</code> is
     *          immutable
     * 
     */
    public void remove() throws NotImplementedException {
      // not implemented
      throw new NotImplementedException(
          "WordGenerator.remove: Doc is immutable");
    }
  }
}
