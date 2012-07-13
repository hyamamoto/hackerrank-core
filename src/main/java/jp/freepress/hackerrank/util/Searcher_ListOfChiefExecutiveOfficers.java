package jp.freepress.hackerrank.util;

import static jp.freepress.hackerrank.util.WikiDicMap.Util.removeParentheisAndWhitespaces;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.MasonTagTypes;
import net.htmlparser.jericho.Segment;
import net.htmlparser.jericho.Source;

/**
 * <i>List_of_chief_executive_officers</i> Map from Wikipedia.<br/>
 * 
 * @author Hiroshi Yamamoto
 * @see http://en.wikipedia.org/wiki/List_of_chief_executive_officers
 */
public class Searcher_ListOfChiefExecutiveOfficers implements WikiDicMap {

  private Map<String, String> ceoMap;

  @Override
  public boolean isSetup() {
    return ceoMap != null;
  }

  @Override
  public void setupMap() {
    String sourceUrlString = "http://en.wikipedia.org/wiki/List_of_chief_executive_officers";

    System.out.println("Requesting: " + "List_of_chief_executive_officers");

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
    // <td><a href="/wiki/AOL_Inc." title="AOL Inc." class="mw-redirect">AOL Inc.</a></td>
    // <td><a href="/wiki/Tim_Armstrong_(executive)" title="Tim Armstrong (executive)">Tim
    // Armstrong</a></td>
    // <td>CEO and Chairman</td>
    // <td>2009</td>
    // <td><a href="/wiki/Connecticut_College" title="Connecticut College">Connecticut
    // College</a></td>
    // <td>Formerly with <a href="/wiki/Google" title="Google">Google</a>.</td>
    // </tr>

    // displaySegments( source.getAllElementsByClass( "flagicon"));
    ceoMap = convertSegments(source.getAllElements(HTMLElementName.TR));

    // Minor fix for the map ( includes, something couldn't have been parsed easily)
    ceoMap.put("", "");

    // NOTE: The number of TD element is different from other TR's. (tr.child.len != 6)
    ceoMap.put("Tata Steel", "B Muthuraman");

  }

  private static Map<String, String> convertSegments(List<? extends Segment> segments) {
    Map<String, String> map = new TreeMap<String, String>();
    for (Segment segment : segments) {
      List<Element> allElements = segment.getChildElements();
      if (allElements.size() != 6) {
        continue;
      }

      Element companyTd = allElements.get(0);
      String company = companyTd.getTextExtractor().toString().trim();
      company = removeParentheisAndWhitespaces(company);

      Element nameTd = allElements.get(1);
      String name = nameTd.getTextExtractor().toString().trim();
      name = removeParentheisAndWhitespaces(name);

      map.put(company, name);
    }
    return map;
  }

  @Override
  public Map<String, String> getMap() {
    return ceoMap;
  }

  public static void main(String[] args) {

    Searcher_ListOfChiefExecutiveOfficers searcher = new Searcher_ListOfChiefExecutiveOfficers();
    searcher.setupMap();
    Map<String, String> map = searcher.getMap();
    for (Map.Entry<String, String> e : map.entrySet()) {
      System.out.println(e.getKey() + " => " + e.getValue());
    }
    System.out.println(map.size());

  }
}
