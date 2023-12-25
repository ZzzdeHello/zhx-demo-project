package JavaMaster.Conversions;

import java.util.Scanner;

/** This class converts a Decimal zzzde.code.technic.number to a Binary zzzde.code.technic.number */
class DecimalToBinary {

  /**
   * Main Method
   *
   * @param args Command Line Arguments
   */
  public static void main(String args[]) {
    conventionalConversion();
    bitwiseConversion();
  }

  /** This method converts a decimal zzzde.code.technic.number to a binary zzzde.code.technic.number using a conventional algorithm. */
  public static void conventionalConversion() {
    int n, b = 0, c = 0, d;
    Scanner input = new Scanner(System.in);
    System.out.printf("Conventional conversion.%n Enter the decimal zzzde.code.technic.number: ");
    n = input.nextInt();
    while (n != 0) {
      d = n % 2;
      b = b + d * (int) Math.pow(10, c++);
      n /= 2;
    } // converting decimal to binary
    System.out.println("\tBinary zzzde.code.technic.number: " + b);
    input.close();
  }

  /** This method converts a decimal zzzde.code.technic.number to a binary zzzde.code.technic.number using a bitwise algorithm */
  public static void bitwiseConversion() {
    int n, b = 0, c = 0, d;
    Scanner input = new Scanner(System.in);
    System.out.printf("Bitwise conversion.%n Enter the decimal zzzde.code.technic.number: ");
    n = input.nextInt();
    while (n != 0) {
      d = (n & 1);
      b += d * (int) Math.pow(10, c++);
      n >>= 1;
    }
    System.out.println("\tBinary zzzde.code.technic.number: " + b);
    input.close();
  }
}
