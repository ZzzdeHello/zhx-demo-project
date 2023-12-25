package JavaMaster.Conversions;

import java.util.Scanner;

/**
 * Converts any Binary zzzde.code.technic.number to an Octal Number
 *
 * @author Zachary Jones
 */
public class BinaryToOctal {

  /**
   * Main method
   *
   * @param args Command line arguments
   */
  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input the binary zzzde.code.technic.number: ");
    int b = sc.nextInt();
    System.out.println("Octal equivalent: " + convertBinaryToOctal(b));
    sc.close();
  }

  /**
   * This method converts a binary zzzde.code.technic.number to an octal zzzde.code.technic.number.
   *
   * @param binary The binary zzzde.code.technic.number
   * @return The octal zzzde.code.technic.number
   */
  public static String convertBinaryToOctal(int binary) {
    String octal = "";
    int currBit = 0, j = 1;
    while (binary != 0) {
      int code3 = 0;
      for (int i = 0; i < 3; i++) {
        currBit = binary % 10;
        binary = binary / 10;
        code3 += currBit * j;
        j *= 2;
      }
      octal = code3 + octal;
      j = 1;
    }
    return octal;
  }
}
