package lect02_th.vehicles;

import utils.NotPossibleException;

/**
 * @overview A test application for vehicles.
 * 
 * @author dmle
 */
public class VehicleApp {
    public static void main(String[] args) {
      // create objects
      Vehicle v;
      try {
        v = new Bus("b1",3,3.0,10.0,5000,40);
        // use objects
        System.out.println(v + ": \n" + v.getName() + 
            ", weight: " + v.calcTotalWeight());
      } catch (NotPossibleException e) {
        e.printStackTrace();
      }
      
      try {
        v = new Car("c1",1.5,1.5,2.5,1500,4);
        System.out.println(v);
      } catch (NotPossibleException e) {
        e.printStackTrace();
      }
    }
}
