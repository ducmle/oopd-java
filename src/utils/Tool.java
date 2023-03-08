package utils;

import java.util.List;


public class Tool {

  /**
   * A method to print an <code>int</code> array.
   * 
   * @param a
   *          the array to print
   * @requires <code>a</code> is not <code>null</code>
   */
  public static void printArray(int[] a) {
    printArray(null, a);
  }

  public static void printArray(int[] a, int highlightPos) {   
    printArray(null, a);
    System.out.print(" "); for (int x = 0; x < highlightPos; x++) { 
      int numSpaces = (a[x]+"").length();
      for (int y = 0; y < numSpaces; y++) System.out.print(" ");
      System.out.print(" ");
    }
    System.out.println("^");    
  }
  
  
  public static void printArray(String var, int[] a) {
    if (var == null)
      var = "";
    else 
      var = var + " = ";
    
    if (a == null) {
      System.out.println(var + "null");
    } else {
      System.out.print(var + "[");
      for (int i = 0; i < a.length; i++) {
        System.out.print(a[i]);
        if (i < a.length - 1)
          System.out.print(",");
      }
      System.out.println("]");
    }
  }

  public static void print(Object o) {
    if (o == null)
      print("null");
    else
      System.out.println(o.toString());
  }

  public static void print(String var, Object o) {
    System.out.println(var + " = " + o);
  }

  /**
   * Formated print
   * 
   * @param str
   *          a formated string
   * @param args
   *          objects to print
   */
  public static void printf(String str, Object... args) {
    System.out.printf(str, args);
  }

  public static void printErr(String mesg) {
    System.err.println(mesg);
  }

  public static void fillList(List l, int untilIndex, Object o) {
    for (int i = l.size(); i <= untilIndex; i++) {
      l.add(o);
    }    
  }
  
  public static boolean equalArrays(int[] a, int[] b) {
    if (a == null || b == null || a.length != b.length) 
      return false;
    
    for (int i = 0; i < a.length; i++) {
      if (a[i] != b[i])
        return false;
    }      
    
    return true;
  }
}
