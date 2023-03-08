package lect01_oopano.ch5.maths;

import utils.DomainConstraint;

/**
 * @overview Polys are immutable polynomials with integer
 *           coefficients. 
 *           A typical Poly is
 *           <code>c0 + c1.x + ... + cn.xn</code>.
 *           
 *           <p>The abstraction function is:<br>
 *           <pre>
 *           AF(c) = c0 + c1x + c2x^2 + ...
 *           where
 *            ci = c.trms[i] if 0 <= i < c.trms.size
 *                 or 0 if otherwise
 *           </pre>
 * @author dmle
 */
public class Poly {
  @DomainConstraint(type="Array",optional=false)
  private int[] trms;
  @DomainConstraint(type="Integer",mutable=false,optional=false)
  private int deg;

  /**
   * @effects Initialises this to be the zero polynomials
   */
  public Poly() {
    trms = new int[1];
    deg = 0;
  }

  /**
   * 
   * @param c
   *          a coefficient
   * @param n
   *          the degree
   * @effects <pre>
   *            if n < 0 
   *              throws NegativeExponentException
   *            else 
   *              initialises this to be c.x^n</pre>.
   */
  public Poly(int c, int n) throws NegativeExponentException {
    if (n < 0)
      throw new NegativeExponentException(
          "Poly(int,int) constructor: negative exponent " + n);

    if (c == 0) {
      trms = new int[1];
      deg = 0;
    } else {
      trms = new int[n + 1];
      // coeffs from x^0 to x^(n-1) are zero
      for (int i = 0; i < n; i++) {
        trms[i] = 0;
      }
      // the last coeff is c
      trms[n] = c;
      deg = n;
    }
  }

  /**
   * @effects <pre>initialises this to be a zero Poly degree n</pre>. 
   */
  private Poly(int n) {
    trms = new int[n + 1];
    deg = n;
  }

  /**
   * @effects <pre>
   *            if this is the zero Poly
   *              returns 0
   *            else 
   *              returns the the largest exponent
   *          with a non-zero coefficient</pre>
   */
  public int degree() {
    return deg;
  }

  /**
   * @effects <pre>returns the coefficient of the term of this whose
   *          exponent is d</pre>
   */
  public int coeff(int d) {
    if (d < 0 || d > deg)
      return 0;
    else
      return trms[d];
  }

  /**
   * @effects <pre>
   *            if q is null 
   *                throws NullPointerException
   *            else 
   *              returns the Poly this + q</pre>
   */
  public Poly add(Poly q) throws NullPointerException {
    Poly la, sm;

    if (deg > q.deg) {
      la = this;
      sm = q;
    } else {
      la = q;
      sm = this;
    }

    int newdeg = la.deg; // uses the larger degree for the new Poly

    // if the two polys have the same degree then check if any of the higher
    // order coefficients are summed to zero, if so decrement the
    // newdeg by the same number
    if (deg == q.deg) { // unless there are trailing zeros
      for (int k = deg; k > 0; k--) {
        if (trms[k] + q.trms[k] != 0)
          break;
        else
          newdeg--;
      }
    }

    // create the new Poly
    Poly r = new Poly(newdeg);
    int i;
    for (i = 0; i <= sm.deg && i <= newdeg; i++) {
      r.trms[i] = sm.trms[i] + la.trms[i];
    }

    for (int j = i; j <= newdeg; j++) {
      r.trms[j] = la.trms[j];
    }

    return r;
  }

  /**
   * 
   * @effects <pre>
   *            if q is null 
   *              throws NullPointerException
   *            else 
   *              returns the Poly this * q</pre>
   */
  public Poly mul(Poly q) throws NullPointerException {
    if (q == null)
      throw new NullPointerException("Poly.mul");
    
    // returns zero poly if one of the polies is zero
    if ((q.deg == 0 && q.trms[0] == 0) || (deg == 0 && trms[0] == 0))
      return new Poly();

    Poly r = new Poly(deg + q.deg);

    r.trms[deg + q.deg] = 0; // prepare to compute coeefs

    for (int i = 0; i <= deg; i++) {
      for (int j = 0; j <= q.deg; j++) {
        r.trms[i+j] = r.trms[i+j] + trms[i] * q.trms[j];
      }
    }

    return r;
  }

  /**
   * @effects <pre>
   *            if q is null 
   *              throws NullPointerException
   *            else 
   *              returns the Poly this - q</pre>
   */
  public Poly sub(Poly q) throws NullPointerException {
    return add(q.minus());
  }

  /**
   * 
   * @effects <pre>returns the Poly - this</pre>
   */
  public Poly minus() {
    Poly r = new Poly(deg);
    for (int i = 0; i <= deg; i++) {
      r.trms[i] = -trms[i];
    }

    return r;
  }

  /// additional methods
  /**
   * @effects <pre>
   *            if q is null 
   *              returns null
   *            else if q.degree equals this.degree and
   *                q.terms equal this.terms (element-by-element)
   *              return true
   *            else
   *              return false</pre>
   */
  public boolean equals(Poly q) {
    if (q == null || deg != q.deg) 
      return false;
    
    for (int i = 0; i <= deg; i++) {
      if (trms[i] != q.trms[i]) return false;
    }
    
    return true;
  }

  @Override
  public boolean equals(Object z) {
    if (! (z instanceof Poly)) return false;
    
    return equals((Poly) z);
  } 
}
