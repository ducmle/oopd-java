package lect02_th.overriding.equality;

public class EqualityExample {
}

class Point3a extends Point2a {
  private int z; // the z coordinate

  public boolean equals(Object p) {
    if (p instanceof Point3a) {
      // overriden behaviour
      return this.equals((Point3a) p);
    } else {
      // supertype's equal behaviour
      return super.equals((Point2a) p);
    }
  }

  public boolean equals(Point3a p) {
    return (p != null && super.equals(p) && z == p.z);
  }
}

class Point2a {
  private int x; // x-coordinate
  private int y; // y-coordinate

  public boolean equals(Object p) {
    if (p instanceof Point2a) {
      // overriden behaviour
      return this.equals((Point2a) p);
    } else {
      // default equal behaviour (last resort)
      return super.equals(p);
    }
  }

  public boolean equals(Point2a p) {
    return (p != null && x == p.x && y == p.y);
  }
}
