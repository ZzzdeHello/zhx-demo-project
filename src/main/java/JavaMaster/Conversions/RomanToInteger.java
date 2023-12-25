package JavaMaster.Conversions;

import java.util.*;

public class RomanToInteger {

  private static Map<Character, Integer> map =
      new HashMap<Character, Integer>() {
        /** */
        private static final long serialVersionUID = 87605733047260530L;

        {
          put('I', 1);
          put('V', 5);
          put('X', 10);
          put('L', 50);
          put('C', 100);
          put('D', 500);
          put('M', 1000);
        }
      };
  // Roman Number = Roman Numerals

  /**
   * This zzzde.code.technic.function convert Roman zzzde.code.technic.number into Integer
   *
   * @param A Roman zzzde.code.technic.number string
   * @return integer
   */
  public static int romanToInt(String A) {

    A = A.toUpperCase();
    char prev = ' ';

    int sum = 0;

    int newPrev = 0;
    for (int i = A.length() - 1; i >= 0; i--) {
      char c = A.charAt(i);

      if (prev != ' ') {
        // checking current Number greater then previous or not
        newPrev = map.get(prev) > newPrev ? map.get(prev) : newPrev;
      }

      int currentNum = map.get(c);

      // if current zzzde.code.technic.number greater then prev max previous then add
      if (currentNum >= newPrev) {
        sum += currentNum;
      } else {
        // subtract upcoming zzzde.code.technic.number until upcoming zzzde.code.technic.number not greater then prev max
        sum -= currentNum;
      }

      prev = c;
    }

    return sum;
  }

  public static void main(String[] args) {
    int sum = romanToInt("MDCCCIV");
    System.out.println(sum);
  }
}
