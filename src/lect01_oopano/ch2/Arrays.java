package lect01_oopano.ch2;

public class Arrays {  
  public static void multiplies(int[] a, int m) {
    if (a == null) return;
    for (int i = 0; i < a.length; i++) {
      a[i] = a[i] * m;
    }
  }
  
  public static void main(String[] args) {
    int[] b = {1,3,5,7,9};
    Arrays.multiplies(b, 2);
  }
}
