package lect01_oopano.ch2;

public class Dispatching {
  public static void main(String[] args) {
    String t = "ab";
    Object o = t + "c"; 
    String r = "abc";
    boolean b=  o.equals(r);
    
    Integer iObj = new Integer(330);
    iObj = Integer.parseInt("330");
    int i = iObj.intValue();
  }
}
