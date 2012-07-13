package jp.freepress.hackerrank.util;

import static jp.freepress.hackerrank.util.PoormanCaesar.ascii26Rotater;
import static jp.freepress.hackerrank.util.PoormanCaesar.lowerAscii26Rotater;
import static jp.freepress.hackerrank.util.PoormanCaesar.rotate;
import static jp.freepress.hackerrank.util.PoormanCaesar.rotateAscii26;
import static jp.freepress.hackerrank.util.PoormanCaesar.rotateLowerAscii26;
import static jp.freepress.hackerrank.util.PoormanCaesar.scanForDeltaAscii26;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.freepress.hackerrank.util.PoormanCaesar.Rotater;
import junit.framework.TestCase;

// NOTE: this really isn't a test. they are just snippets
public class PoormanCaesarTest extends TestCase {

  final List<String> textList = Arrays.asList("htktc iwdjhpcs, udjg wjcsgts pcs txvwin-hxm",
      "lpnoa aovbzhuk, vul obukylk huk aopyaf-zpe", "uijsuz-gpvs",
      "vul aovbzhuk, mpcl obukylk huk zlcluaf-mvby", "cfx cqxdbjwm, oren qdwmanm jwm cfnwch",
      "ftdqq ftagemzp, ftdqq tgzpdqp mzp fiqzfk", "jzo kyfljreu, vzxyk yleuivu reu wzwkp-kyivv",
      "ilyh wkrxvdqg, hljkw kxqguhg dqg hljkwhhq", "tsj ymtzxfsi, jnlmy mzsiwji fsi knkyd-tsj",
      "zlclu aovbzhuk, lpnoa obukylk huk lpnoaf-lpnoa",
      "wzmv kyfljreu, knf yleuivu reu wzwkp-wfli", "ocmzz ocjpnviy, zdbco cpiymzy viy ndsot-idiz",
      "wayzl lzgmksfv, xgmj zmfvjwv sfv lzajlq-kwnw",
      "gfw lzgmksfv, wayzl zmfvjwv sfv fafwlq-wayzl",
      "guerr gubhfnaq, rvtug uhaqerq naq fvkgl-avar", "iwgtt iwdjhpcs pcs uxuin-udjg",
      "pof ivoesfe boe uijsuz-fjhiu", "yxo dryeckxn, cofox rexnbon kxn dgoxdi-osqrd",
      "nzqzi ocjpnviy, nzqzi cpiymzy viy idizot", "ihy bohxlyx uhx nbclns-ycabn",
      "dct wjcsgts pcs iwxgin-txvwi");

  public static void testAscii26Rotater() {

    String text = "Oziuqvqdwzwca";
    String target = "Oziuqvqdwzwca";
    ascii26RotateSub(text, target);

    text = "Kpxxtaszmtl";
    target = "Kpxxtaszmtl";
    ascii26RotateSub(text, target);

    // // Amman, Qccqd
    text = "atexsdeitgdadvn";
    target = "atexsdeitgdadvn";
    ascii26RotateSub(text, target);

    text = "Zenzizenzizenzic";
    target = "Zenzizenzizenzic";
    ascii26RotateSub(text, target);

    
    

    System.out.println("abcdefghijklmnopqursuvwxyz");
    System.out.println(rotateLowerAscii26("abcdefghijklmnopqursuvwxyz", 'q' - 'a'));

    System.out.println(scanForDeltaAscii26("Amman", "Qccqd", '_'));
    System.out.println(scanForDeltaAscii26("Qccqd", "Amman", '_'));
    System.out.println(rotateAscii26("Amman", 16));
    System.out.println(rotateAscii26("Qccqd", 10));

  }

  private static void ascii26RotateSub(String text, String target) {

    if (text.length() != target.length()) {
      System.out.println("ERROR");
      return;
    }
    List<Rotater> poormen = new ArrayList<PoormanCaesar.Rotater>();
    for (int i = 0; i < 26; i++) {
      poormen.add(ascii26Rotater(i));
    }
    int maxScore = 0;
    String maxScoreText = null;
    for (Rotater r : poormen) {
      String a = new String(rotate(r, text.toCharArray()));
      System.out.println(a);
      int score = 0;
      for (int i = 0; i < target.length(); i++) {
        char textCh = a.charAt(i);
        char targetCh = target.charAt(i);
        if (textCh == targetCh) {
          score++;
        }
      }
      if (score > maxScore) {
        maxScoreText = a;
      }
    }
    if (maxScoreText != null) {
      System.out.println(maxScoreText);
    }
  }

  public void testLowerAscii26Rotater(List<String> textList) {

    List<Rotater> poormen = new ArrayList<PoormanCaesar.Rotater>();
    for (int i = 0; i < 26; i++) {
      poormen.add(lowerAscii26Rotater(i));
    }
    for (int k = 0; k < 1; k++) {
      for (String text : textList) {
        int maxScore = 0;
        String maxScoreText = null;
        for (Rotater r : poormen) {
          String a = new String(rotate(r, text.toCharArray()));
          int score = 0;
          for (Num num : Num.values()) {
            if (a.indexOf(num.asStr()) != -1) {
              score++;
            }
          }
          if (score > maxScore) {
            maxScoreText = a;
          }
        }
        if (maxScoreText != null) {
          System.out.println(maxScoreText);
        }
      }
    }

  }

  public void testScoreMatch(List<String> textList) {

    for (int k = 0; k < 1; k++) {
      for (String text : textList) {
        int maxScore = 0;
        String maxScoreText = null;
        for (int i = 0; i < 26; i++) {
          String a = rotateLowerAscii26(text, i);
          int score = 0;
          for (Num num : Num.values()) {
            if (a.indexOf(num.asStr()) != -1) {
              score++;
            }
          }
          if (score > maxScore) {
            maxScoreText = a;
          }
        }
        if (maxScoreText != null) {
          System.out.println(maxScoreText);
        }
      }
    }
  }

  // public void testRotateLowerAscii26() {
  // fail("Not yet implemented");
  // }
  //
  // public void testRotateAscii26() {
  // fail("Not yet implemented");
  // }
  //
  // public void testRotateAscii96() {
  // fail("Not yet implemented");
  // }


}
