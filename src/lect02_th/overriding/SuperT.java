package lect02_th.overriding;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class SuperT implements Cloneable {
  public Number method1(Number n) {
    return 1;
  }  

  public void method2(Number n) throws IOException, RuntimeException {
    //
  }  
  
  public void method3() throws FileNotFoundException {
    //
  }    
}
