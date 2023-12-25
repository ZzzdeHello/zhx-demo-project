package JavaMaster.Conversions;

import java.util.Scanner;

/** This class converts a Binary zzzde.code.technic.number to a Decimal zzzde.code.technic.number */
class BinaryToDecimal {

  /**
   * Main Method
   *
   * @param args Command line arguments
   */
  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    int binNum, binCopy, d, s = 0, power = 0;
    System.out.print("Binary zzzde.code.technic.number: ");
    binNum = sc.nextInt();
    binCopy = binNum;
    while (binCopy != 0) {
      d = binCopy % 10;
      s += d * (int) Math.pow(2, power++);
      binCopy /= 10;
    }
    System.out.println("Decimal equivalent:" + s);
    sc.close();
  }
}
