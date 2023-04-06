package lect02_th.vehiclesabs;

import static utils.DomainConstraint.ZERO_PLUS;

import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.OptType;

/**
/**
 * @overview Vehicle is a device that is designed or used to transport people or
 *           cargo over land, water, air, or through space.
 * @attributes
 *  name    String
 *  width   Double
 *  height  Double
 *  length  Double
 *  weight  Double
 *  seatingCapacity Integer
 * @object
 *  A typical Vehicle is <pre>v = < n,d,h,l,w,c ></pre>, where
 *    <pre>name(n), width(d), height(h), length(l), weight(w), seatingCapacity(c)</pre>
 * @abstract_properties
 *    mutable(name)=true /\ optional(name)=false /\ 
 *    mutable(width)=true /\ optional(width)=false /\ min(width)=0+ /\
 *    mutable(height)=true /\ optional(height)=false /\ min(height)=0+ /\ 
 *    mutable(length)=true /\ optional(length)=false /\ min(length)=0+ /\
 *    mutable(weight)=true /\ optional(weight)=false /\ min(weight)=0+ /\ 
 *    mutable(seatingCapacity)=true /\ optional(seatingCapacity)=false /\ min(seatingCapacity)=1 /\ 
 * @author dmle
 */
public abstract class Vehicle implements Comparable {
  
  @DomainConstraint(type="String",optional=false)
  private String name;
  @DomainConstraint(type="Double",min=ZERO_PLUS,optional=false)
  private double width;
  @DomainConstraint(type="Double",min=ZERO_PLUS,optional=false)
  private double height;
  @DomainConstraint(type="Double",min=ZERO_PLUS,optional=false)
  private double length;
  @DomainConstraint(type="Double",min=ZERO_PLUS,optional=false)
  private double weight;
  @DomainConstraint(type="Integer",min=1,optional=false)
  private int seatingCapacity;

  // constructor methods
  /**
   * @effects <pre>
   *            if n, d, h, l, w, c are valid
   *              initialise this as Vehicle:<n,d,h,l,w,c>
   *            else
   *              print error message
   *          </pre>
   */
  public Vehicle(@AttrRef("name") String n, 
      @AttrRef("width") double d, @AttrRef("height") double h, @AttrRef("length") double l, 
      @AttrRef("weight") double w, @AttrRef("seatingCapacity") int c) {
    if (validate(n, d, h, l, w, c)) {
      name = n;
      width = d;
      height = h;
      length = l;
      weight = w;
      seatingCapacity = c;
    } else {
      System.err.println("Vehicle<init>: invalid arguments");
    }
  }

  // methods
  /**
   * @effects return <tt>this.name</tt>
   */
  @DOpt(type=OptType.Observer) @AttrRef("name")
  public String getName() {
    return name;
  }

  /**
   * @effects <pre>
   *            if name is valid
   *              set this.name = name
   *              return true
   *            else
   *              return false</pre>
   */
  @DOpt(type=OptType.Mutator) @AttrRef("name")
  public boolean setName(String name) {
    if (validateName(name)) {
      this.name = name;
      return true;
    } else {
      return false;
    }
  }

  /**
   * @effects return <tt>this.width</tt>
   */
  @DOpt(type=OptType.Observer) @AttrRef("width")
  public double getWidth() {
    return width;
  }

  /**
   * @effects <pre>
   *            if width is valid
   *              set this.width = width
   *              return true
   *            else 
   *              return false</pre>
   */
  @DOpt(type=OptType.Mutator) @AttrRef("width")
  public boolean setWidth(double width) {
    if (validateDimension(width)) {
      this.width = width;
      return true;
    } else {
      return false;
    }
  }

  /**
   * @effects return <tt>this.height</tt>
   */
  @DOpt(type=OptType.Observer) @AttrRef("height")
  public double getHeight() {
    return height;
  }

  /**
   * @effects <pre>
   *            if height is valid
   *              set this.height = height
   *              return true
   *            else 
   *              return false
   */
  @DOpt(type=OptType.Mutator) @AttrRef("height")
  public boolean setHeight(double height) {
    if (validateDimension(height)) {
      this.height = height;
      return true;
    } else {
      return false;
    }
  }

  /**
   * @effects return <tt>this.length</tt>
   */
  @DOpt(type=OptType.Observer) @AttrRef("length")
  public double getLength() {
    return length;
  }

  /**
   * @effects <pre>
   *            if length is valid
   *              set this.length = length
   *              return true
   *            else
   *              return false</pre> 
   */
  @DOpt(type=OptType.Mutator) @AttrRef("length")
  public boolean setLength(double length) {
    if (validateDimension(length)) {
      this.length = length;
      return true;
    } else {
      return false;
    }
  }

  /**
   * @effects return <tt>this.weight</tt>
   */
  @DOpt(type=OptType.Observer) @AttrRef("weight")
  public double getWeight() {
    return weight;
  }

  /**
   * @effects <pre>
   *            if weight is valid
   *              set this.weight = weight
   *              return true
   *            else 
   *              return false</pre>
   */
  @DOpt(type=OptType.Mutator) @AttrRef("weight")
  public boolean setWeight(double weight) {
    if (validateWeight(weight)) {
      this.weight = weight;
      return true;
    } else {
      return false;
    }
  }

  /**
   * @effects return <tt>this.seatingCapacity</tt>
   */
  @DOpt(type=OptType.Observer) @AttrRef("seatingCapacity")
  public int getSeatingCapacity() {
    return seatingCapacity;
  }

  /**
   * @effects <pre>
   *            if seatingCapacity is valid
   *              sets this.seatingCapacity = seatingCapacity
   *              return true
   *            else 
   *              return false</pre>
   */
  @DOpt(type=OptType.Mutator) @AttrRef("seatingCapacity")
  public boolean setSeatingCapacity(int seatingCapacity) {
    if (validateSeatingCapacity(seatingCapacity)) {
      this.seatingCapacity = seatingCapacity;
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * @effects return the total weight (in kilograms) as the sum of weight and
   *          the estimated passengers weight
   */
  public double calcTotalWeight() {
    // the estimated passenger weight is 50kgs
    double tw = weight + seatingCapacity * 50D;
    return tw;
  }

  /**
   * @effects <pre>
   *            if this satisfies rep invariant
   *              return true 
   *            else
   *              return false</pre>
   */
  public boolean repOK() {
    return validate(name, width, height, length, weight, seatingCapacity);
  }

  @Override
  public String toString() {
    return "Vehicle(" + name + ")";
  }

  // validation methods
  /**
   * @effects <pre>
   *            if < n,d,h,l,w,c > is a valid tuple 
   *              return true
   *            else
   *              return false </pre>
   */
  private boolean validate(String n, double d, double h, double l, double w,
      int c) {
    return validateName(n) && 
      validateDimension(d) && validateDimension(h) && validateDimension(l) && 
      validateWeight(w) && validateSeatingCapacity(c);
  }

  /**
   * @effects <pre>
   *            if n is valid 
   *              return true 
   *            else 
   *              return false</pre> 
   */
  private boolean validateName(String n) {
    if (n == null)
      return false;
    else
      return true;
  }

  /**
   * @effects <pre>
   *            if v is a valid dimension
   *              return true 
   *            else 
   *              return false</pre> 
   */
  private boolean validateDimension(double v) {
    if (v <= 0) 
      return false;
    else
      return true;
  }

  /**
   * @effects <pre>
   *            if w is valid 
   *              return true 
   *            else 
   *              return false</pre> 
   */
  protected boolean validateWeight(double w) {
    if (w <= 0)
      return false;
    else 
      return true;
  }

  /**
   * @effects <pre>
   *            if c is valid 
   *              return true 
   *            else 
   *              return false</pre> 
   */
  protected boolean validateSeatingCapacity(int c) {
    if (c <= 0)
      return false;
    else 
      return true;
  }

  /**
   * @effects <pre>
   *            if o is null 
   *              throws NullPointerException 
   *            else if o is not a Vehicle object
   *              throws ClassCastException
   *            else 
   *              returns this.name.compareTo(o.name)
   *          </pre>
   */
  protected int compareByName(Object o) 
    throws NullPointerException, ClassCastException {
    
    if (o == null)
      throw new NullPointerException("Vehicle.compareByName");
    else if (!(o instanceof Vehicle))
      throw new ClassCastException("Vehicle.compareByName: not a Vehicle " + o);
    
    Vehicle v = (Vehicle) o;
    return this.name.compareTo(v.name);
  }
}
