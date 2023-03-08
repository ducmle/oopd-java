package utils;

/**
 * @overview 
 *  Represents the <b>essential</b> operation types that appear in a domain class.
 *  
 * @author dmle
 *
 * @version 2017
 */
public enum OptType {
  /**constructor (creator) operation*/
  Constructor,
  /**mutator operation (e.g. setter) */
  Mutator, 
  /**observer operation (e.g. getter)*/
  Observer, 
  /**default operation (e.g. toString)*/
  Default,
  /**helper operation (e.g. data validation) */
  Helper,
  /**a specialised type of observer, which is used to annotate an operation of a collection class
   * that realises its primary <b>iterator abstraction</b> */
  ObserverIterator,
  /**a specialised type of observer, which is used to annotate an operation of a collection class
   * that checks for <b>membership of an element</b> in a collection */
  ObserverContains,
  /**a specialised type of observer, which is used to annotate an operation of a collection class
   * that returns its <b>size</b>*/
  ObserverSize, 
  /**a specialised type of mutator, which is used to annotate an operation of a collection class
   * that <b>adds</b> a new element to the collection*/
  MutatorAdd, 
  /**a specialised type of mutator, which is used to annotate an operation of a collection class
   * that <b>removes</b> an element from the collection*/
  MutatorRemove,
  /**equals() method */
  DefaultEquals, 
  /**Not yet classified*/
  Other, 
  ;
  
  /**
   * @effects 
   *  if this represents a type of Mutator
   *    return true
   *  else
   *    return false
   */
  public boolean isMutator() {
    return name().startsWith(Mutator.name());
  }
  
  /**
   * @effects 
   *  if this represents a type of Observer
   *    return true
   *  else
   *    return false
   */
  public boolean isObserver() {
    return name().startsWith(Observer.name());
  }

  /**
   * @effects 
   *  if this is {@link #Constructor} 
   */
  public boolean isConstructor() {
    return this.equals(Constructor);
  }

  /**
   * @effects 
   *  if this is {@link #Default} 
   */
  public boolean isDefault() {
    return this.equals(Default);
  }

  /**
   * @effects 
   *  if this is {@link #Helper} 
   */
  public boolean isHelper() {
    return this.equals(Helper);
  }

  /**
   * @effects 
   * is this {@link #Other}?
   */
  public boolean isOther() {
    return this.equals(Other);
  }
}
