package jp.freepress.hackerrank.util;

import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;

interface SearchDic {}


// XXX: Many System.out is left on code.

/**
 * Birth place search by <tt>Wolfram*Alpha</tt>. Basically this just returns the top answer for the
 * given question. "Where is the birth place of {PERSON_NAME} ?" Note that this Computational
 * Knowledge Engine sometimes gives a wrong answer <code>&gt;.&gt;</code> ... <br/>
 * (when happens, it returns a date instead of a name of the place.)
 * 
 * @author HiroshiYamamoto
 * @see http://www.wolframalpha.com/
 */
public class Searcher_BirthPlace implements SearchDic {

  private boolean setup = false;

  private final String appID;

  public Searcher_BirthPlace(String appID) {
    super();
    this.appID = appID;
  }

  public void setup() {
    setup = true;
  }

  public boolean isSetup() {
    return setup;
  }

  public String search(String text) {
    WAEngine engine = new WAEngine();

    // These properties will be set in all the WAQuery objects created from this WAEngine.
    engine.setAppID(appID);
    engine.addFormat("plaintext");

    // Create the query.
    WAQuery query = engine.createQuery();

    query.setInput(text);

    try {
      // For educational purposes, print out the URL we are about to send:
      // System.out.println("Query URL:");
      // System.out.println(engine.toURL(query));
      // System.out.println("");

      // This sends the URL to the Wolfram|Alpha server, gets the XML result
      // and parses it into an object hierarchy held by the WAQueryResult object.
      WAQueryResult queryResult = engine.performQuery(query);

      if (queryResult.isError()) {
        System.out.println("Query error");
        System.out.println("  error code: " + queryResult.getErrorCode());
        System.out.println("  error message: " + queryResult.getErrorMessage());

        // error code 1: Invalid appid
      } else if (!queryResult.isSuccess()) {
        System.out.println("Query was not understood; no results available.");
      } else {
        // Got a result.
        // System.out.println("Successful query. Pods follow:\n");
        for (WAPod pod : queryResult.getPods()) {
          if (!pod.isError()) {
            String title = pod.getTitle();
            if ("Result".equals(title)) {
              // System.out.println(title);
              // System.out.println("------------");
              for (WASubpod subpod : pod.getSubpods()) {
                for (Object element : subpod.getContents()) {
                  if (element instanceof WAPlainText) {
                    WAPlainText plainText = (WAPlainText) element;
                    String str = plainText.getText();
                    return str;
                    // System.out.println(str);
                    // System.out.println("");
                  }
                }
              }
            }
            // System.out.println("");
          }
        }
      }
    } catch (WAException e) {
      e.printStackTrace();
    }

    return null;
  }

  public static void main(String[] args) {
    Searcher_BirthPlace search = new Searcher_BirthPlace("");
    String result;
    result = search.search("Where was Yasser Arafat born?");
    System.out.println(result);
  }

}
