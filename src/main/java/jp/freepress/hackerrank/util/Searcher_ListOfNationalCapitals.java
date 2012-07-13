package jp.freepress.hackerrank.util;

import static jp.freepress.hackerrank.util.WikiDicMap.Util.removeParentheisAndWhitespaces;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.MasonTagTypes;
import net.htmlparser.jericho.Segment;
import net.htmlparser.jericho.Source;

// http://en.wikipedia.org/wiki/List_of_national_capitals

/**
 * <i>ist_of_national_capitals</i> Map from Wikipedia.
 */
public class Searcher_ListOfNationalCapitals implements WikiDicMap {

  private Map<String, String> capitalsMap;

  @Override
  public boolean isSetup() {
    return capitalsMap != null;
  }

  @Override
  public void setupMap() {
    String sourceUrlString = "http://en.wikipedia.org/wiki/List_of_national_capitals";

    System.out.println("Requesting: " + "List_of_national_capitals");

    MasonTagTypes.register();
    Source source;
    try {
      source = new Source(new URL(sourceUrlString));
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    source = new Source(source);

    // displaySegments( source.getAllElements( HTMLElementName.H1));

    // <tr>
    // <td><a href="/wiki/Algiers" title="Algiers">Algiers</a></td>
    // <td><b><span class="flagicon"><img alt=""
    // src="//upload.wikimedia.org/wikipedia/commons/thumb/7/77/Flag_of_Algeria.svg/22px-Flag_of_Algeria.svg.png"
    // width="22" height="15"
    // class="thumbborder" />&#160;</span><a href="/wiki/Algeria"
    // title="Algeria">Algeria</a></b></td>
    // <td></td>
    // </tr>

    // displaySegments( source.getAllElementsByClass( "flagicon"));
    capitalsMap = convertSegments(source.getAllElements(HTMLElementName.TR));

    // Minor fix for the map ( includes, something couldn't have been parsed easily)
    capitalsMap.put("", "");

    // NOTE: Includes "note C." annotation "[c]"... Really?
    capitalsMap.put("Somaliland[c]", "Hargeisa");
    // ? 10287
    capitalsMap.put("Jordan", "Amman");



  }

  private static Map<String, String> convertSegments(List<? extends Segment> segments) {
    Map<String, String> map = new HashMap<String, String>();
    for (Segment segment : segments) {
      List<Element> flagIcon = segment.getAllElementsByClass("flagicon");
      if (flagIcon.isEmpty()) {
        continue;
      }
      List<Element> allElements = segment.getChildElements();

      Element capitalTd = allElements.get(0);
      String capital = capitalTd.getTextExtractor().toString().trim();
      capital = removeParentheisAndWhitespaces(capital);
      // System.out.print(capital + ", ");

      Element countryTd = allElements.get(1);
      String country = countryTd.getTextExtractor().toString().trim();
      country = removeParentheisAndWhitespaces(country);
      // System.out.println( country);

      map.put(country, capital);
    }
    return map;
  }

  @Override
  public Map<String, String> getMap() {
    return capitalsMap;
  }

  public static void main(String[] args) {

    Searcher_ListOfNationalCapitals searcher = new Searcher_ListOfNationalCapitals();
    searcher.setupMap();
    Map<String, String> map = searcher.getMap();
    for (Map.Entry<String, String> e : map.entrySet()) {
      System.out.println(e.getKey() + " => " + e.getValue());
    }
    System.out.println(map.size());

  }
}
