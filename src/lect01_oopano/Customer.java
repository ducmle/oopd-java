package lect01_oopano;

import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;

/**
 * @overview Customers are people or organisations with which we have
 *           relationships.
 * @attributes 
 *   id    Integer  
 *   name  String   
 * @object A typical Customer is c=<d,n>, where id(d), name(n).
 * @abstract_properties
 *   mutable(id)=false /\ optional(id)=false /\ min(id)=1 /\ 
 *   mutable(name)=true /\ optional(name)=false /\ length(name)=50
 * @author dmle
 */
public class Customer {
  @DomainConstraint(type = "Integer", mutable = false, 
      optional = false, min = MIN_ID)
  private int id;

  @DomainConstraint(type = "String", optional = false, length = LENGTH_NAME)
  private String name;

  // constants
  private static final int MIN_ID = 1;
  private static final int LENGTH_NAME = 50;

  // constructor methods
  /**
   * @effects <pre> 
   *  if custID, name are valid
   *    initialise this as <custID,name>
   *  else
   *    initialise this as <> and inform error</pre>
   */
  //@DOpt(type=OptType.Constructor)
  public Customer(@AttrRef("id") int custID, @AttrRef("name") String name) 
      throws NotPossibleException {
    // if custID, name are valid
    if (!validateId(custID)) {
      throw new NotPossibleException("Customer.init: Invalid customer id: " + custID);
    }
    
    if (!validateName(name)) {
      throw new NotPossibleException("Customer.init: Invalid customer name: " + name);
    }
    
    // initialise this as <custID,name>
    id = custID;
    this.name = name;
  }

  /**
   * @effects <pre>
   *   if name is valid
   *     set this.name=name
   *     return true
   *   else
   *     return false</pre>
   */
  @DOpt(type=OptType.Mutator) 
  @AttrRef("name")
  public boolean setName(String name) {
    if (validateName(name)) {
      this.name = name;
      return true;
    } else {
      return false;
    }
  }

  /**
   * @effects return <tt>id</tt>
   */
  @DOpt(type=OptType.Observer) @AttrRef("id")
  public int getId() {
    return id;
  }

  /**
   * 
   * @effects return <tt>name</tt>
   */
  @DOpt(type=OptType.Observer) 
  @AttrRef("name")  
  public String getName() {
    return name;
  }

  /**
   * @effects <pre>
   *  if id is valid 
   *    return true 
   *  else
   *    return false
   *  </pre>
   */
  private boolean validateId(int id) {
    return id >= MIN_ID;
  }

  /**
   * @effects <pre>
   *   if name is valid 
   *     return true
   *   else 
   *     return false</pre>
   */
  private boolean validateName(String name) {
    return (name != null && 
        name.length() > 0 && 
        name.length() <= LENGTH_NAME);
  }  

  @Override
  public String toString() {
    return "Customer:<" + id + "," + name + ">";
  }

  /**
   * Java's specification (omitted)
   * 
   * Indicates whether some other object is "equal to" this one.
   * 
   * The equals method implements an equivalence relation on non-null object references:
   * <ul>
   *   <li>It is reflexive: for any non-null reference value x, x.equals(x) should return true.
   *   <li>It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.
   *   <li>It is transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true.
   *   <li>It is consistent: for any non-null reference values x and y, multiple invocations of x.equals(y) consistently return true or consistently return false, provided no information used in equals comparisons on the objects is modified.
   * </ul>
   * 
   * For any non-null reference value x, x.equals(null) should return false.
   * The equals method for class Object implements the most discriminating possible equivalence relation on objects; 
   *  that is, for any non-null reference values x and y, 
   *  this method returns true if and only if x and y refer to the same object 
   *    (x == y has the value true).
   * 
   * Note that it is generally necessary to override the hashCode method whenever this method is overridden, 
   * so as to maintain the general contract for the hashCode method, 
   * which states that equal objects must have equal hash codes.
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Customer))
      return false;

    int yourID = ((Customer) o).id;
    return yourID == id;
  }

  /**
   * Java's specification (omitted):
   * 
   * <div>Returns a hash code value for the object. 
   * 
   * This method is supported for the benefit of hash tables 
   * such as those provided by java.util.HashMap.
   * 
   * <div>The <b>general contract</b> of hashCode is:
   * 
   * <div>Whenever it is invoked on the same object more than once during an execution of a 
   * Java application, the hashCode method must consistently return the same integer, 
   * provided no information used in equals comparisons on the object is modified. 
   * This integer need not remain consistent from one execution of an application 
   * to another execution of the same application.
   * 
   * <div>If two objects are equal according to the equals(Object) method, 
   * then calling the hashCode method on each of the two objects must produce the 
   * same integer result.
   * 
   * <div>It is not required that if two objects are unequal according to the 
   * java.lang.Object.equals(java.lang.Object) method, 
   * then calling the hashCode method on each of the two objects must 
   * produce distinct integer results. 
   * However, the programmer should be aware that producing distinct integer 
   * results for unequal objects may improve the performance of hash tables.
   */
  @Override
  public int hashCode() {
    return id;
  }
  
  /**
   * @effects <pre>
   *   if this satisfies abstract properties
   *     return true 
   *   else
   *     return false</pre>
   */
  public boolean repOK() {
    return validateId(id) && validateName(name);
  }
}
