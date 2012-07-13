package jp.freepress.hackerrank.util;

import java.util.Collection;
import java.util.HashMap;

public enum Num {
  ZERO(0, "zero"), //
  ONE(1, "one"), //
  TWO(2, "two"), //
  THREE(3, "three"), //
  FOUR(4, "four"), //
  FIVE(5, "five"), //
  SIX(6, "six"), //
  SEVEN(7, "seven"), //
  EIGHT(8, "eight"), //
  NINE(9, "nine"), //
  TEN(10, "ten"), //
  ELEVEN(11, "eleven"), //
  TWELVE(12, "twelve"), //
  THIRTEEN(13, "thirteen"), //
  FOURTEEN(14, "fourteen"), //
  FIFTEEN(15, "fifteen"), //
  SIXTEEN(16, "sixteen"), //
  SEVENTEEN(17, "seventeen"), //
  EIGHTEEN(18, "eighteen"), //
  NINETEEN(19, "nineteen"), //
  TWENTY(20, "twenty"), //
  TWENTY_ONE(21, "twenty-one"), //
  TWENTY_TWO(22, "twenty-two"), //
  TWENTY_THREE(23, "twenty-three"), //
  TWENTY_FOUR(24, "twenty-four"), //
  TWENTY_FIVE(25, "twenty-five"), //
  TWENTY_SIX(26, "twenty-six"), //
  TWENTY_SEVEN(27, "twenty-seven"), //
  TWENTY_EIGHT(28, "twenty-eight"), //
  TWENTY_NINE(29, "twenty-nine"), //
  THIRT_(30, "thirty"), //
  THIRTY_ONE(31, "thirty-one"), //
  THIRTY_TWO(32, "thirty-two"), //
  THIRTY_THREE(33, "thirty-three"), //
  THIRTY_FOUR(34, "thirty-four"), //
  THIRTY_FIVE(35, "thirty-five"), //
  THIRTY_SIX(36, "thirty-six"), //
  THIRTY_SEVEN(37, "thirty-seven"), //
  THIRTY_EIGHT(38, "thirty-eight"), //
  THIRTY_NINE(39, "thirty-nine"), //
  FORTY(40, "fourty"), // aah, forty or fourty?
  FORTY_ONE(41, "fourty-one"), //
  FORTY_TWO(42, "fourty-two"), //
  FORTY_THREE(43, "fourty-three"), //
  FORTY_FOUR(44, "fourty-four"), //
  FORTY_FIVE(45, "fourty-five"), //
  FORTY_SIX(46, "fourty-six"), //
  FORTY_SEVEN(47, "fourty-seven"), //
  FORTY_EIGHT(48, "fourty-eight"), //
  FORTY_NINE(49, "fourty-nine"), //
  FIFTY(50, "fifty"), //
  FIFTY_ONE(51, "fifty-one"), //
  FIFTY_TWO(52, "fifty-two"), //
  FIFTY_THREE(53, "fifty-three"), //
  FIFTY_FOUR(54, "fifty-four"), //
  FIFTY_FIVE(55, "fifty-five"), //
  FIFTY_SIX(56, "fifty-six"), //
  FIFTY_SEVEN(57, "fifty-seven"), //
  FIFTY_EIGHT(58, "fifty-eight"), //
  FIFTY_NINE(59, "fifty-nine"), //
  SIXTY(60, "sixty"), //
  SIXTY_ONE(61, "sixty-one"), //
  SIXTY_TWO(62, "sixty-two"), //
  SIXTY_THREE(63, "sixty-three"), //
  SIXTY_FOUR(64, "sixty-four"), //
  SIXTY_FIVE(65, "sixty-five"), //
  SIXTY_SIX(66, "sixty-six"), //
  SIXTY_SEVEN(67, "sixty-seven"), //
  SIXTY_EIGHT(68, "sixty-eight"), //
  SIXTY_NINE(69, "sixty-nine"), //
  SEVENTY(70, "seventy"), //
  SEVENTY_ONE(71, "seventy-one"), //
  SEVENTY_TWO(72, "seventy-two"), //
  SEVENTY_THREE(73, "seventy-three"), //
  SEVENTY_FOUR(74, "seventy-four"), //
  SEVENTY_FIVE(75, "seventy-five"), //
  SEVENTY_SIX(76, "seventy-six"), //
  SEVENTY_SEVEN(77, "seventy-seven"), //
  SEVENTY_EIGHT(78, "seventy-eight"), //
  SEVENTY_NINE(79, "seventy-nine"), //
  EIGHTY(80, "eighty"), //
  EIGHTY_ONE(81, "eighty-one"), //
  EIGHTY_TWO(82, "eighty-two"), //
  EIGHTY_THREE(83, "eighty-three"), //
  EIGHTY_FOUR(84, "eighty-four"), //
  EIGHTY_FIVE(85, "eighty-five"), //
  EIGHTY_SIX(86, "eighty-six"), //
  EIGHTY_SEVEN(87, "eighty-seven"), //
  EIGHTY_EIGHT(88, "eighty-eight"), //
  EIGHTY_NINE(89, "eighty-nine"), //
  NINETY_(90, "ninety"), //
  NINETY_ONE(91, "ninety-one"), //
  NINETY_TWO(92, "ninety-two"), //
  NINETY_THREE(93, "ninety-three"), //
  NINETY_FOUR(94, "ninety-four"), //
  NINETY_FIVE(95, "ninety-five"), //
  NINETY_SIX(96, "ninety-six"), //
  NINETY_SEVEN(97, "ninety-seven"), //
  NINETY_EIGHT(98, "ninety-eight"), //
  NINETY_NINE(99, "ninety-nine");

  final int number;

  final String numberStr;

  private Num(int number, String numberStr) {
    this.number = number;
    this.numberStr = numberStr;
  }

  public int asInt() {
    return number;
  }

  public String asStr() {
    return numberStr;
  }

  public int getStrLength() {
    return numberStr.length();
  }

  public int matchForScore(String candi) {
    return matchForScore(asStr(), candi);
  }

  public static Num findBestMatch(String candi, String original, Collection<Character> resolvedChars) {
    Num bestNum = null;
    int bestScore = 0;
    NUMS: for (Num num : Num.values()) {
      int score = num.matchForScore(candi);

      // check contradiction
      if (original != null && original.length() == num.asStr().length()) {
        HashMap<Character, Character> dupeCheckMap = new HashMap<Character, Character>();
        HashMap<Character, Character> dupeCheckMap2 = new HashMap<Character, Character>();
        for (int i = 0; i < num.asStr().length(); i++) {
          char numCh = num.asStr().charAt(i);
          char candiCh = original.charAt(i);
          if (dupeCheckMap.containsKey(candiCh) && dupeCheckMap.get(candiCh) != numCh) {
            continue NUMS;
          }
          if (dupeCheckMap2.containsKey(numCh) && dupeCheckMap2.get(numCh) != candiCh) {
            continue NUMS;
          }
          dupeCheckMap.put(candiCh, numCh);
          dupeCheckMap2.put(numCh, candiCh);
        }
        // this test better gives +1 score
      }

      // skipping based on resolved chars
      if (resolvedChars != null) {
        for (char resolvedChar : resolvedChars) {
          if (candi.indexOf(resolvedChar) == -1 && num.asStr().indexOf(resolvedChar) != -1) {
            continue NUMS;
          }
        }
      }

      if (score > 0 && score == bestScore) {


        // can't have two candidates with the same score
        // System.err.println( "DOUBLE: " + candi + " = " + bestNum + " or " + num + ": =" + score);
        bestNum = null;
      }
      if (score > bestScore) {
        bestScore = score;
        bestNum = num;
      }
    }
    if (bestNum != null) {
      return bestNum;
    }
    return null;
  }



  static int matchForScore(String orig, String candi) {
    if (candi.length() != orig.length()) {
      return 0;
    }
    int score = 1; // 1 point for length match
    for (int i = 0; i < candi.length(); i++) {
      char candi_char = candi.charAt(i);
      char my_char = orig.charAt(i);
      if (candi_char == my_char) {
        score++;
      }
    }
    return score;
  }

}
