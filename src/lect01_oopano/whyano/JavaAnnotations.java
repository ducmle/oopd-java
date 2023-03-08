package lect01_oopano.whyano;

import java.lang.annotation.Repeatable;

/**
 * @overview 
 *
 * @author Duc Minh Le (ducmle)
 *
 * @version 
 */
public class JavaAnnotations {
  @Override
  public boolean equals(Object o) { return this == o; }
  
  @SuppressWarnings("unchecked")
  void myMethod() { }
  
  @Author(name = "Jane Doe")
  class MyClassA { }
  
  @Author(name = "Jane Doe")
  @Author(name = "John Smith")
  class MyClassB { }
  
  @Repeatable(value = Authors.class)
  public @interface Author{
    String name();
  }
  public @interface Authors {
    Author[] value();
  }
}
