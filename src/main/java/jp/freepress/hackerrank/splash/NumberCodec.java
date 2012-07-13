package jp.freepress.hackerrank.splash;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jp.freepress.hackerrank.util.Num;
import jp.freepress.hackerrank.util.Num00;

/**
 * Decoder for SpaceX problem.
 * 
 * @author Hiroshi Yamamoto
 */
public class NumberCodec {
  HashMap<Character, Character> map = new HashMap<Character, Character>();

  HashMap<Character, Character> map2 = new HashMap<Character, Character>();
  {
    map.put(',', ',');
    map.put('-', '-');
    map.put('.', '.');
    map.put(' ', ' ');
    map2.put(',', ',');
    map2.put('-', '-');
    map2.put('.', '.');
    map2.put(' ', ' ');
  }

  public void addSeed(String seed1, String seed2) {
    if (seed1.length() != seed2.length()) {
      throw new IllegalArgumentException("seed1.length <> seed2.length ( \"" + seed1 + "\", \""
          + seed2 + "\")");
    }
    for (int i = 0; i < seed1.length(); i++) {
      char sc1 = Character.toLowerCase(seed1.charAt(i));
      char sc2 = Character.toLowerCase(seed2.charAt(i));
      map.put(sc1, sc2);
      map2.put(sc2, sc1);
    }
  }

  public static void decode(String text, String firstOrig, String firstDecrypt) {
    NumberCodec t = new NumberCodec();
    String seed1;
    String seed2;

    seed1 = firstOrig;
    seed2 = firstDecrypt;
    t.addSeed(seed1, seed2);

    seed1 = "hundred";
    seed2 = t.findNum00(text, Num00.HUNDRED);
    if (seed2 != null) {
      t.addSeed(seed1, seed2);
    }

    seed1 = "thousand";
    seed2 = t.findNum00(text, Num00.THOUSAND);
    if (seed2 != null) {
      t.addSeed(seed1, seed2);
    }

    String andStr = t.findAnd(text);
    if (andStr != null && andStr.length() == 3) {
      t.addSeed("and", andStr);
    }

    for (int k = 0; k < 5; k++) {
      // wash, rinse, repeat
      Map<String, String> map =
          t.extractProbableStringFromIncompletedStr(text, t.build(text, true));
      for (Map.Entry<String, String> e : map.entrySet()) {
        seed1 = e.getValue();
        seed2 = e.getKey();
        // System.out.println( e);
        t.addSeed(seed1, seed2);
      }
    }

    t.dump(text);

    // return build( text);
  }

  public String findAnd(String text) {
    String[] tokens = text.trim().split(" ");
    if (tokens.length < 5 && text.indexOf(",") != -1) {
      // "one thousand, three hundred" => ng
      return null;
    }
    // "one thousand, one hundred and three" => ok
    // "one hundred and three" => ok
    if (tokens.length > 3 && tokens[tokens.length - 2].length() == 3) {
      return tokens[tokens.length - 2];
    }
    return null;
  }

  public String findNum00(String text, Num00 target) {
    text = replaceAnd(text, ", ");
    String[] tokens00 = text.split(",");
    Collection<Character> resolvedChars = map.keySet();
    for (int idx = 0; idx < tokens00.length; idx++) {
      String token00 = tokens00[idx].trim();
      String[] tokens = token00.split(" ");
      switch (tokens.length) {
        case 2: {
          Num00 bestNum00 = Num00.findBestMatch(tokens[1], true, resolvedChars);
          if (bestNum00 == target) {
            return tokens[1];
          }
        }
      }
    }
    return null;
  }

  public int incompletedStrToNumber(String text) {
    text = replaceAnd(text, ", ");
    String[] tokens00 = text.split(",");
    Collection<Character> resolvedChars = map.keySet();
    int unknownCount = 0;
    int numberTotal = 0;
    for (int idx = 0; idx < tokens00.length; idx++) {
      String token00 = tokens00[idx].trim();
      String[] tokens = token00.split(" ");
      switch (tokens.length) {
        case 1: {
          Num bestNum = Num.findBestMatch(tokens[0], null, resolvedChars);
          if (bestNum != null) {
            numberTotal += bestNum.asInt();
          } else {
            // System.err.println( "UNKNOWN: " + token00 + " => " + bestNum);
            unknownCount++;
          }
          break;
        }
        case 2: {
          Num00 bestNum00 = Num00.findBestMatch(tokens[1], true, resolvedChars);
          Num bestNum = Num.findBestMatch(tokens[0], null, resolvedChars);
          if (bestNum00 != null && bestNum != null) {
            numberTotal += bestNum00.asInt() * bestNum.asInt();
          } else {
            // System.err.println( "UNKNOWN: " + token00 + " => " + bestNum00 + ", " + bestNum);
            unknownCount++;
          }
          break;
        }
        default:
          new IllegalStateException();
      }
    }
    return unknownCount == 0 ? numberTotal : -1;
  }

  public String replaceAnd(String text, String with) {
    String andStr = findAnd(text);
    return text.replace(" " + andStr + " ", with);
  }

  public Map<String, String> extractProbableStringFromIncompletedStr(String originalText,
      String partialText) {
    HashMap<String, String> newHintMap = new HashMap<String, String>();
    originalText = replaceAnd(originalText, ", ");
    partialText = replaceAnd(partialText, ", ");
    String[] partialTokens00 = partialText.split(",");
    String[] originalTokens00 = originalText.split(",");
    Collection<Character> resolvedChars = map.keySet();

    for (int idx = 0; idx < partialTokens00.length; idx++) {
      String token00 = partialTokens00[idx].trim();
      String originalToken00 = originalTokens00[idx].trim();
      String[] tokens = token00.split(" ");
      String[] originalTokens = originalToken00.split(" ");
      switch (tokens.length) {
        case 1: {
          Num bestNum = Num.findBestMatch(tokens[0], originalTokens[0], resolvedChars);
          if (bestNum != null) {
            newHintMap.put(originalTokens[0], bestNum.asStr());
          }
          break;
        }
        case 2: {
          Num00 bestNum00 = Num00.findBestMatch(tokens[1], true, resolvedChars);
          Num bestNum = Num.findBestMatch(tokens[0], originalTokens[0], resolvedChars);
          if (bestNum00 != null && bestNum != null) {
            newHintMap.put(originalTokens[0], bestNum.asStr());
          }
          break;
        }
        default:
          new IllegalStateException();
      }
    }
    return newHintMap;
  }

  public String build(String text, boolean maskUnknown) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < text.length(); i++) {
      Character c = text.charAt(i);
      Character cn = map2.get(Character.toLowerCase(c));
      if (cn == null) {
        cn = c;
        sb.append(maskUnknown ? '_' : cn);
      } else {
        sb.append(cn);
      }
    }
    return sb.toString();
  }

  protected void dump(String test) {
    StringBuilder sb_error = new StringBuilder();
    StringBuilder sb_mixed = new StringBuilder();
    StringBuilder sb_masked = new StringBuilder();
    for (int i = 0; i < test.length(); i++) {
      Character c = test.charAt(i);
      Character cn = map2.get(Character.toLowerCase(c));
      if (cn == null) {
        cn = c;
        sb_error.append("'");
        sb_mixed.append(cn);
        sb_masked.append("_");
      } else {
        sb_error.append(" ");
        sb_mixed.append(cn);
        sb_masked.append(cn);
      }
    }

    System.out.println("    : " + sb_error.toString());
    System.out.println("ORIG: " + test);
    System.out.println("GEN : " + sb_masked.toString());
    // System.out.println( sb_mixed.toString());

    int result = incompletedStrToNumber(sb_masked.toString());
    System.out.println("VAL : " + result);
    System.out.println("--");
  }
}
