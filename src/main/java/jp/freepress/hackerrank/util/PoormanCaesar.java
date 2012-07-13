package jp.freepress.hackerrank.util;


/**
 * <b><code>Caesar Cipher</code></b><br/>
 * Poor man listens, poorman rotates, then poorman answers.<br/>
 * 
 * @author Hiroshi Yamamoto
 */
public class PoormanCaesar {

  public static Rotater lowerAscii26Rotater(final int shift) {
    return new Rotater() {
      @Override
      public char rotate(char c) {
        if (c >= 'a' && c <= 'z') {
          return (char) ((c - 'a' + shift) % 26 + 'a');
        }
        return c;
      }
    };
  }

  public static Rotater ascii26Rotater(final int shift) {
    return new Rotater() {
      @Override
      public char rotate(char c) {
        if (c >= 'a' && c <= 'z') {
          return (char) ((c - 'a' + shift) % 26 + 'a');
        }
        if (c >= 'A' && c <= 'Z') {
          return (char) ((c - 'A' + shift) % 26 + 'A');
        }
        return c;
      }
    };
  }

  public static Rotater ascii96Rotater(final int shift) {
    return new Rotater() {
      @Override
      public char rotate(char c) {
        if (c >= 32 && c <= 127) {
          // Change base to make life easier, and use an
          // int explicitly to avoid worrying... cast later
          int x = c - 32;
          x = (x + shift) % 96;
          if (x < 0) x += 96; // java modulo can lead to negative values!
          return (char) (x + 32);
        }
        return c;
      }
    };
  }

  public static String rotateLowerAscii26(String text, int shift) {
    return new String(rotate(lowerAscii26Rotater(shift), text.toCharArray()));
  }

  public static String rotateAscii26(String text, int shift) {
    return new String(rotate(ascii26Rotater(shift), text.toCharArray()));
  }

  public static String rotateAscii96(String text, final int shift) {
    return new String(rotate(ascii96Rotater(shift), text.toCharArray()));
  }

  public static char[] rotate(Rotater r, char[] chars) {
    for (int i = 0; i < chars.length; i++) {
      char c = chars[i];
      char nc = r.rotate(c);
      chars[i] = nc;
    }
    return chars;
  }

  // Check if the given two text have a delta.
  public static int scanForDeltaAscii26(String text, String maskedText, char charToIgnore) {
    if (text == null || maskedText == null || text.isEmpty()
        || text.length() != maskedText.length()) {
      return -1;
    }
    int diff = -1;
    for (int i = 0; i < maskedText.length(); i++) {
      char textCh = text.charAt(i);
      char targetCh = maskedText.charAt(i);
      if (Character.isLetter(targetCh) && Character.isLetter(textCh) && targetCh != charToIgnore
          && textCh != charToIgnore) {
        int newDelta = targetCh - textCh;
        newDelta = newDelta < 0 ? newDelta + 26 : newDelta;
        if (diff != -1 && newDelta != diff) {
          diff = -1; // most likely a malformed text.
          break;
        } else {
          diff = newDelta;
        }
      }
    }
    return diff;
  }

  public static interface Rotater {
    public char rotate(char c);
  }

}
