package lect02_th.vehiclesextra;

import java.util.Arrays;

import lect02_th.vehicles.Vehicle;
import utils.AttrRef;
import utils.DomainConstraint;
import utils.NotPossibleException;

/**
 * @overview Bus is a sub-class of Vehicle representing a bus (also omnibus or
 *           autobus), which is a road vehicle designed to carry passengers. Buses have
 *           a capacity as high as 300 passengers and are widely used for public
 *           transportation.
 * @attributes
 *  routes Integer[]  int[]
 * @abstract_properties
 *    P_Vehicle /\ 
 *    min(weight)=5000 /\
 *    min(seatingCapacity)=30 /\ 
 *    mutable(routes)=false /\ optional(routes)=false /\
 *    length(routes) > 0
 * @author dmle
 */
public class Bus extends Vehicle {
  private static final double MIN_WEIGHT = 5000;
  private static final double MIN_SEATCAP = 30;

  // extra attribute
  @DomainConstraint(type="Integer[]",mutable=false,optional=false)
  private int[] routes;
  
  // constructor methods
  /**
   * @effects <pre>
   *            if n, d, h, l, w, c, routes are valid
   *              initialise this as Bus:<n,d,h,l,w,c,routes>
   *            else
   *              throws NotPossibleException 
   *          </pre>
   */
  public Bus(@AttrRef("name") String n, 
      @AttrRef("width") double d, @AttrRef("height") double h, @AttrRef("length") double l, 
      @AttrRef("weight") double w, @AttrRef("seatingCapacity") int c, 
      @AttrRef("routes") int[] routes) throws NotPossibleException {
    super(n, d, h, l, w, c);
    
    if (!validateRoutes(routes)) {
      throw new NotPossibleException("Bus.init: invalid routes: " + 
            Arrays.toString(routes));
    } else {
      this.routes = routes;
    }
  }
  
  /**
   * @effects 
   *  return a copy of routes
   */
  public int[] getRoutes() {
    // create an array of the same size
    int[] copyRoutes = new int[routes.length];
    // copy elements over 
    System.arraycopy(routes, 0, copyRoutes, 0, routes.length);
    
    // return the copy array
    return copyRoutes;
  }

  @Override
  public boolean repOK() {
    // validate other attributes first
    if (super.repOK()) {
      // validate routes
      return validateRoutes(routes);
    } else {
      return false;
    }
  }
  
  @Override  
  public String toString() {
    return "Bus(" + getName() + ")";
  }
  
  /**
   * @effects <pre>
   *            if w is valid 
   *              return true 
   *            else 
   *              return false</pre> 
   */
  @Override
  @DomainConstraint(type="Double",min=MIN_WEIGHT,optional=false)
  protected boolean validateWeight(double w) {
    if (w < MIN_WEIGHT)
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
  @Override
  @DomainConstraint(type="Integer",min=MIN_SEATCAP,optional=false)
  protected boolean validateSeatingCapacity(int c) {
    if (c < MIN_SEATCAP)
      return false;
    else 
      return true;
  }
  
  /**
   * @effects 
   *  if routes is valid
   *    return true
   *  else
   *    return false
   */
  private boolean validateRoutes(int[] routes) {
    if (routes != null && routes.length > 0) {
      return true;
    } else {
      return false;
    }
  }
}
