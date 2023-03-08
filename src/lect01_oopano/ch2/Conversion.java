package lect01_oopano.ch2;

public class Conversion {
  public static void main(String[] args) {
    short s = 0;
    
    byte b = 0;
    s = b;
    
    char c = 'A';
    //illegal
    //c = s;
    
    int i = s;
    i = b;
    i = c;
    
    long l = i;
    l = b;
    l = s;
    l = c;
    
    float f = b;
    System.out.println("f: " + f);
    f = s;
    System.out.println("f: " + f);
    f = c;
    System.out.println("f: " + f);
    f = i;
    System.out.println("f: " + f);
    f = l;
    System.out.println("f: " + f);
    
    // illegal
    //l = f;
    
    double d = l;
    d = f;    
  }
}
