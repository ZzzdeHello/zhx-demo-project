package JavaMaster.Conversions;

import java.util.Scanner;

/**
 * Converts any Octal Number to a Decimal Number
 *
 * @author Zachary Jones
 */
public class OctalToDecimal {

  /**
   * Main method
   *
   * @param args Command line arguments
   */
  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Octal Input: ");
    String inputOctal = sc.nextLine();
    int result = convertOctalToDecimal(inputOctal);
    if (result != -1) System.out.println("Result convertOctalToDecimal : " + result);
    sc.close();
  }

  /**
   * This method converts an octal zzzde.code.technic.number to a decimal zzzde.code.technic.number.
   *
   * @param inputOctal The octal zzzde.code.technic.number
   * @return The decimal zzzde.code.technic.number
   */
  public static int convertOctalToDecimal(String inputOctal) {

    try {
      // Actual conversion of Octal to Decimal:
      Integer outputDecimal = Integer.parseInt(inputOctal, 8);
      return outputDecimal;
    } catch (NumberFormatException ne) {
      // Printing a warning message if the input is not a valid octal
      // zzzde.code.technic.number:
      System.out.println("Invalid Input, Expecting octal zzzde.code.technic.number 0-7");
      return -1;
    }
  }
}
