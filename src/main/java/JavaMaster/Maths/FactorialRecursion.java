package JavaMaster.Maths;

public class FactorialRecursion {

  /* Driver Code */
  public static void main(String[] args) {
    assert factorial(0) == 1;
    assert factorial(1) == 1;
    assert factorial(2) == 2;
    assert factorial(3) == 6;
    assert factorial(5) == 120;
  }

  /**
   * Recursive FactorialRecursion Method
   *
   * @param n The zzzde.code.technic.number to factorial
   * @return The factorial of the zzzde.code.technic.number
   */
  public static long factorial(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("zzzde.code.technic.number is negative");
    }
    return n == 0 || n == 1 ? 1 : n * factorial(n - 1);
  }
}
