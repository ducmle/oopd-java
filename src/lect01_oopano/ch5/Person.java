package lect01_oopano.ch5;

import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.OptType;

/**
 * @overview A human being that is characterised by attributes such as id and name
 * @attributes
 *  id      Integer         int
 *  name    String      
 *  
 * @object
 *  A typical Person is <i,n> where id(i), name(n)
 * 
 * @abstract_properties
 *  mutable(id)=false /\ optional(id)=false /\ min(id)=1 /\
 *  mutable(name)=true /\ optional(name)=false /\ length(name)=30
 *  
 * @author dmle
 */
public class Person {
  @DomainConstraint(type="Integer",mutable=false,optional=false,min=MIN_ID)
  private int id;

  @DomainConstraint(type="String",mutable=true,optional=false,length=LENGTH_NAME)
  private String name;

  // constants
  private static final int MIN_ID = 1;
  private static final int LENGTH_NAME = 30;
  
  /**
   * @effects 
   *  if id, name are valid
   *      initialise this as <id,name>
   *  else
   *      initialise this as <> and print error
   */
  // not needed: @DOpt(type=OptType.Constructor)
  public Person(
      @AttrRef("id") int id, 
      @AttrRef("name") String name) {
    if (!validateId(id)) {
      // id is invalid, initialise this as <> and print error
      System.err.println("Person.init: invalid id: " + id);
      return;
    }

    if (!validateName(name)) {
      // name is invalid, initialise this as <> and print error
      System.err.println("Person.init: invalid name: " + name);
      return;
    }

    // id and name are both valid: initialise this as <id,name>
    this.id = id;
    this.name = name;
  }
  
  /**
   * @effects 
   *  return id
   */
  @DOpt(type=OptType.Observer)
  @AttrRef("id")
  public int getId() {
    return id;
  }
  
  /**
   * @effects 
   *  return name
   */
  @DOpt(type=OptType.Observer)
  @AttrRef("name")
  public String getName() {
    return name;
  }
  
  /**
   * @modifies this.name
   * @effects 
   *  if name is valid
   *      set this.name = name
   *  else
   *      do nothing
   */
  @DOpt(type=OptType.Mutator)
  @AttrRef("name")
  public void setName(
      // (not needed) @AttrRef(name="name") 
      String name) {
    if (validateName(name)) {
      this.name = name;
    }
  }

  @DOpt(type=OptType.Default)
  @Override
  public String toString() {
    String str;
    /* use String */
    str = "<" + id + ", " + name + ">";
    /* use StringBuffer */
    /*
     * StringBuffer sb = new StringBuffer(); sb.append("<").
     * append(id).append(", "). append(name).append(", ").
     * append(phone).append(">"); str = sb.toString();
     */

    return str;
  }
  
  /**
   * @effects 
   *  if this is valid with regards to abstract properties
   *      return true
   *  else
   *      return false
   */
  public boolean repOK() {
    if (validateId(id) && validateName(name)) {
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * @effects 
   *  if id is valid
   *      return true
   *  else
   *      return false
   */
  private boolean validateId(int id) {
    // because id is primitive type, there no need to validate the optional
    // constraint
    // validate min constraint
    if (id < MIN_ID) {
      return false;
    } else {
      return true;
    }
  }
  
  /**
   * @effects 
   *  if name is valid
   *      return true
   *  else
   *      return false
   */
  private boolean validateName(String name) {
    // check optional constraint
    if (name == null || name.length() == 0) {
      return false;
    }

    // check length constraint
    if (name.length() > LENGTH_NAME) {
      return false;
    }

    return true;
  }
} /** end {@link Person} */
