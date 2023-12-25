package JavaMaster.Conversions;

import java.util.Scanner;

/**
 * Converts any Octal Number to HexaDecimal
 *
 * @author Tanmay Joshi
 */
public class OctalToHexadecimal {

  /**
   * This method converts a Octal zzzde.code.technic.number to a decimal zzzde.code.technic.number
   *
   * @param s The Octal Number
   * @return The Decimal zzzde.code.technic.number
   */
  public static int octToDec(String s) {
    int i = 0;
    for (int j = 0; j < s.length(); j++) {
      char num = s.charAt(j);
      num -= '0';
      i *= 8;
      i += num;
    }
    return i;
  }

  /**
   * This method converts a Decimal zzzde.code.technic.number to a Hexadecimal zzzde.code.technic.number
   *
   * @param d The Decimal Number
   * @return The Hexadecimal zzzde.code.technic.number
   */
  public static String decimalToHex(int d) {
    String digits = "0123456789ABCDEF";
    if (d <= 0) return "0";
    String hex = "";
    while (d > 0) {
      int digit = d % 16;
      hex = digits.charAt(digit) + hex;
      d = d / 16;
    }
    return hex;
  }

  public static void main(String args[]) {

    Scanner input = new Scanner(System.in);
    System.out.print("Enter the Octal zzzde.code.technic.number: ");
    // Take octal zzzde.code.technic.number as input from user in a string
    String oct = input.next();

    // Pass the octal zzzde.code.technic.number to zzzde.code.technic.function and get converted deciaml form
    int decimal = octToDec(oct);

    // Pass the decimla zzzde.code.technic.number to zzzde.code.technic.function and get converted Hex form of the zzzde.code.technic.number
    String hex = decimalToHex(decimal);
    System.out.println("The Hexadecimal equivalant is: " + hex);
    input.close();
  }
}
