package lect01_oopano.ch2;

public class ObjectAndVariable {
  public void fig22a() {
    int i = 6;
    int j;  // uninitialised
    int[] a = {1,3,5,7,9};  // creates a 5-element array
    int[] b = new int[3];
    String s = "abcdef";    // creates a new string
    String t = null;
  }
  
  public void fig22b() {
    int i = 6;
    int j;  // uninitialised
    int[] a = {1,3,5,7,9};  // creates a 5-element array
    int[] b = new int[3];
    String s = "abcdef";    // creates a new string
    String t = null;

    j = i;
    b = a;
    t = s;
  }
}
