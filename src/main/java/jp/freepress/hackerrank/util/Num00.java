package jp.freepress.hackerrank.util;

import java.util.Collection;


public enum Num00 {
  THOUSAND(1000, "thousand"), //
  HUNDRED(100, "hundred"), //
  MILLION(1000000, "million");

  final int number;

  final String numberStr;

  private Num00(int number, String numberStr) {
    this.number = number;
    this.numberStr = numberStr;
  }

  public int asInt() {
    return number;
  }

  public String asStr() {
    return numberStr;
  }

  public int matchForScore(String candi) {
    return Num.matchForScore(asStr(), candi);
  }

  public static Num00 findBestMatch(String candi, boolean lengthMatch,
      Collection<Character> resolvedChars) {
    Num00 bestNum = null;
    int bestScore = 0;
    NUMS: for (Num00 num : Num00.values()) {
      if (num.asStr().length() == candi.length()) {
        return num;
      }
      int score = num.matchForScore(candi);
      if (score > 0 && score == bestScore) {
        // skipping based on resolved chars
        if (resolvedChars != null) {
          for (char resolvedChar : resolvedChars) {
            if (candi.indexOf(resolvedChar) == -1 && num.asStr().indexOf(resolvedChar) != -1) {
              continue NUMS;
            }
          }
        }
        // can't have two candidates
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
}
