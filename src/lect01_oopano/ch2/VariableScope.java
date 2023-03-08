package lect01_oopano.ch2;

public class VariableScope {
  /** class variable */
  private static final int MAX = 100; 
  
  /** object variable */
  private int v; // initialised to 0
  
  public int getValue() {
    return v;
  }
  
  public int nextValue() {
    // local variable
    int next = v + 1;
    
    if (next > MAX) next = 0;
    
    v = next;
    
    return next;
  }
}
