package lect02_th.overriding.equality;

public class BetterEqualityExample {
}

class Point3 extends Point2 {
  private int z; // the z coordinate

  public boolean equals(Object p) {
    // equal based on same class
    if (p == null || p.getClass() != this.getClass())
      return false;
    
    return this.equals((Point3) p);
  }

  public boolean equals(Point3 p) {
    return (p != null && super.equals(p) && z == p.z);
  }
}

class Point2 {
  private int x; // x-coordinate
  private int y; // y-coordinate

  public boolean equals(Object p) {
    // equal based on same class
    if (p == null || p.getClass() != this.getClass())
      return false;

    return equals((Point2) p);
  }

  public boolean equals(Point2 p) {
    return (p != null && x == p.x && y == p.y);
  }
}
