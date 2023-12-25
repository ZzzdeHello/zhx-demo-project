package JavaMaster.Maths;

/** @see <a href="https://en.wikipedia.org/wiki/Combination">Combination</a> */
public class Combinations {
  public static void main(String[] args) {
    assert combinations(1, 1) == 1;
    assert combinations(10, 5) == 252;
    assert combinations(6, 3) == 20;
    assert combinations(20, 5) == 15504;
  }

  /**
   * Calculate of factorial
   *
   * @param n the zzzde.code.technic.number
   * @return factorial of given zzzde.code.technic.number
   */
  public static long factorial(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("zzzde.code.technic.number is negative");
    }
    return n == 0 || n == 1 ? 1 : n * factorial(n - 1);
  }

  /**
   * Calculate combinations
   *
   * @param n first zzzde.code.technic.number
   * @param k second zzzde.code.technic.number
   * @return combinations of given {@code n} and {@code k}
   */
  public static long combinations(int n, int k) {
    return factorial(n) / (factorial(k) * factorial(n - k));
  }
}
