package utils;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @overview Defines a domain constraint of some attribute
 * @author dmle
 *
 */
@Target(value={FIELD, METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DomainConstraint {  
  
  /***
   * Represents 0+, used to specify the min of real-type attributes 
   *  whose values are positive
   */
  public static final double ZERO_PLUS = Double.MIN_VALUE/Double.MAX_VALUE; 

  /***
   * Represents 0-, used to specify the max of real-type attributes 
   *  whose values are negative
   */
  public static final double ZERO_MINUS = -Double.MIN_VALUE/Double.MAX_VALUE;
  
  public String type() default "null";
  public boolean mutable() default true;
  public boolean optional() default true;
  public int length() default  -1;
  public double min() default Double.NaN;
  public double max() default Double.NaN;
}
