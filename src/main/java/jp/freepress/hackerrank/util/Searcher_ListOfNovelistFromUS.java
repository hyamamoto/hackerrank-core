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
 * <i>List_of_novelists_from_the_United_States</i> Map from Wikipedia.
 * 
 * @see http://en.wikipedia.org/wiki/List_of_novelists_from_the_United_States
 */
public class Searcher_ListOfNovelistFromUS implements WikiDicMap {

  private Map<String, String> bookAuthorMap;

  @Override
  public boolean isSetup() {
    return bookAuthorMap != null;
  }

  @Override
  public void setupMap() {
    String sourceUrlString =
        "http://en.wikipedia.org/wiki/List_of_novelists_from_the_United_States";

    System.out.println("Requesting: " + "List_of_novelists_from_the_United_States");

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

    // <li><a href="/wiki/Doris_Betts" title="Doris Betts">Doris Betts</a> (born 1932), <i><a
    // href="/w/index.php?title=Souls_Raised_from_the_Dead&amp;action=edit&amp;redlink=1"
    // class="new"
    // title="Souls Raised from the Dead (page does not exist)">Souls Raised from the
    // Dead</a></i></li>

    // displaySegments( source.getAllElements( HTMLElementName.LI));
    bookAuthorMap = convertSegments(source.getAllElements(HTMLElementName.LI));

    // Minor fix for the map ( includes, something couldn't have been parsed easily)

    // NOTE: Too many links in a book name.
    bookAuthorMap.put("Sarah", "JT LeRoy"); // // JT LeRoy (Laura Albert) (born 1965), Sarah
    bookAuthorMap
        .put(
            "Epilogue and Testimony of Kohen Ben Gadol, The Red Letters, The Last Sounding of the Shofar, The Torah, B'reishith,The Complete Book of Genesis, Judeo/Christian Qur'an, The Red Letters, My Isha, Psalms of Praise",
            "Sir Robin Douglass Shepard");
    // NOTE: 2 authors for one book.
    bookAuthorMap.put("Watch Your Mouth", "Daniel Handler");
    // NOTE: The author has a comma in his name.
    bookAuthorMap.put("Ragged Dick", "Horatio Alger, Jr.");
    // NOTE: The title attribute and the actual link text are different.
    bookAuthorMap.put("Lamb in His Bosom", "Caroline Miller");
    bookAuthorMap.put("The Balloonist", "MacDonald Harris"); // Donald Heiney
    bookAuthorMap.put("Time Will Darken It", "William Maxwell"); // William Keepers Maxwell, Jr.
    bookAuthorMap.put("The Store", "T. S. Stribling"); // Thomas Sigismund Stribling
    // NOTE: Double authors.
    // "Ellery Queen (Frederick Dannay (1905-1982) and \nManfred B. Lee (1905-1971), The Greek Coffin Mystery"
    bookAuthorMap.put("The Greek Coffin Mystery", "Ellery Queen");
    // NOTE: Double parenthesis, double authors, and a line-break.
    // "Emma Lathen (Martha Hennissart (born 1929) and Mary Jane \nLatsis (1927-1997)), Murder Against the Grain"
    bookAuthorMap.put("Murder Against the Grain", "Emma Lathen");
    // NOTE: A comma at the end of the name and it counts!
    // "Faith Baldwin, (1893-1978), The Heart Has Wings"
    bookAuthorMap.put("The Heart Has Wings", "Faith Baldwin,");
    bookAuthorMap.put("Darwin's Radio", "Greg Bear,");
    bookAuthorMap.put("Stet", "James Chapman,");
    bookAuthorMap.put("Chilly Scenes of Winter", "Ann Beattie,");

    // NOTE: An anchor tag between double-quotes.
    bookAuthorMap.put("\"Elegies for the Water\"", "Philip Lee Williams");
    // NOTE: An anchor tag between double-quotes. And this time, remove double-quotes!
    // "Robert Elegant (born 1928) \"Dynasty\""
    bookAuthorMap.put("Dynasty", "Robert Elegant");
    // NOTE: has "write as" in between.
    // "Margaret Astrid Lindholm Ogden (born 1952), writes as Robin Hobb & Megan Lindholm"
    bookAuthorMap.put("writes as Robin Hobb & Megan Lindholm", "Margaret Astrid Lindholm Ogden");

  }

  private static Map<String, String> convertSegments(List<? extends Segment> segments) {
    Map<String, String> map = new TreeMap<String, String>();
    for (Segment segment : segments) {
      Element element = segment.getFirstElement();
      String elementContent = element.getTextExtractor().toString();
      if (elementContent.indexOf("), ") == -1 && elementContent.indexOf(") ") == -1
          && elementContent.indexOf(" (") == -1) {
        continue;
      }
      List<Element> allElements = element.getChildElements();
      Element authorAnchor = allElements.get(0);
      if (authorAnchor.getName().equalsIgnoreCase("ul")) {
        continue;
      }
      if (!authorAnchor.getName().equalsIgnoreCase("a")) {
        throw new IllegalStateException();
      }
      String authorTitle = authorAnchor.getAttributeValue("title").trim();
      authorTitle = removeParentheisAndWhitespaces(authorTitle);
      String author = authorAnchor.getTextExtractor().toString();
      if (!authorTitle.equals(author)) {
        // System.err.println("author name and title are inconsistent: \"" + authorTitle + "\", \""
        // + author + "\"");
        author = removeParentheisAndWhitespaces(author.trim());
      }
      // System.out.print(author + ", ");
      String book;
      if (allElements.size() > 1) {
        Element bookAnchor = allElements.get(1);
        book = bookAnchor.getTextExtractor().toString().trim();
      } else {
        // this is rough!
        book = elementContent.substring(elementContent.lastIndexOf(")") + 1).trim();
      }
      book = removeParentheisAndWhitespaces(book);

      // System.out.println( book);
      map.put(book, author);
    }
    return map;
  }

  @Override
  public Map<String, String> getMap() {
    return bookAuthorMap;
  }

  public static void main(String[] args) {

    Searcher_ListOfNovelistFromUS searcher = new Searcher_ListOfNovelistFromUS();
    searcher.setupMap();
    Map<String, String> map = searcher.getMap();
    for (Map.Entry<String, String> e : map.entrySet()) {
      System.out.println(e.getKey() + " => " + e.getValue());
    }
    System.out.println(map.size());

  }
}
