package jp.freepress.hackerrank.util;

import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Segment;

public interface WikiDicMap {

  public void setupMap();

  public boolean isSetup();

  public Map<String, String> getMap();

  public static final class Util {

    public static String removeParentheisAndWhitespaces(String text) {
      return text.replaceAll("\\([^\\(]*\\)", "").replaceAll("\\[[^\\(]*\\]", "")
          .replaceAll("\\s+", " ").trim();
    }

    public static void displaySegments(List<? extends Segment> segments) {
      for (Segment segment : segments) {
        System.out
            .println("-------------------------------------------------------------------------------");
        System.out.println(segment.getDebugInfo());
        System.out.println(segment);
      }
    }

  }

}
