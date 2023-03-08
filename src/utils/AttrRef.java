package utils;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @overview 
 *  Represents method references to attributes of the same class. 
 *  
 * @example
 *  The following example demonstrates how to use {@link AttrRef} to declare that 
 *  method <tt>Customer.getName</tt> references the attribute <tt>Customer.name</tt>
 *  <pre>
 *  class Customer {
 *    private String name;
 *    
 *    &#064;AttrRef("name")
 *    public String getName() {
 *      return name;
 *    }
 *  }
 *  </pre>
 *  
 *  <p>The following example demonstrates how to use {@link AttrRef} to declare that 
 *  the parameter <tt>name</tt> of a <tt>Customer</tt>'s constructor reference the respected 
 *  attribute <tt>Customer.name</tt>
 *  <pre>
 *  class Customer {
 *    private String name;
 *    
 *    public Customer(&#064;AttrRef("name") String name) {
 *      this.name = name;
 *    }
 *  }
 *  </pre>
 *  
 * @author dmle
 *
 * @version 2017
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})
@Documented
public @interface AttrRef {
  String value(); 
  
  ElementType type() default FIELD;
}
