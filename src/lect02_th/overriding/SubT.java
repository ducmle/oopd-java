package lect02_th.overriding;

import java.io.FileNotFoundException;
import java.io.IOException;

import utils.NotPossibleException;

public class SubT extends SuperT {

  // return type is subtype
  public Integer method1(Number n) {
    return 1;
  }
  
  // exceptions: subset
  @Override
  //public void method2(Number n) {
  //public void method2(Number n) throws RuntimeException {
  public void method2(Number n) throws IOException {
    //
  }  

  // exceptions: new unchecked
  @Override
  //public void method3() throws NotPossibleException {
  public void method3() throws FileNotFoundException, NotPossibleException {
    //
  }  

  // return type is subtype
  @Override  
  public SubT clone() {
    return new SubT();
  }
}
