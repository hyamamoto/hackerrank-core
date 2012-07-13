package jp.freepress.hackerrank.splash;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.freepress.hackerrank.core.CoreConstants;
import jp.freepress.hackerrank.util.Searcher_BirthPlace;
import jp.freepress.hackerrank.util.Searcher_ListOfChiefExecutiveOfficers;
import jp.freepress.hackerrank.util.Searcher_ListOfNationalCapitals;
import jp.freepress.hackerrank.util.Searcher_ListOfNovelistFromUS;
import jp.freepress.hackerrank.util.WikiDicMap;

/**
 * Parse questions, and performs search.
 * 
 * @author Hiroshi Yamamoto
 * @see Searcher_ListOfNovelistFromUS
 * @see Searcher_ListOfNationalCapitals
 * @see Searcher_ListOfChiefExecutiveOfficers
 * @see Searcher_BirthPlace
 */
public class FuzzyDicSearch {

  private static final boolean SETUPALL_ON_STARTUP = false;

  private WikiDicMap listOfNovelist;
  private WikiDicMap listOfCapitals;
  private WikiDicMap listOfCEO;
  private Searcher_BirthPlace placeOfBirth;

  private Pattern patternNovelist = Pattern.compile("is the author of the book (.*)\\?");
  private Pattern patternCapital = Pattern.compile("is the capital of (.*)\\?");
  private Pattern patternCEO = Pattern.compile("is the CEO of (.*)\\?");
  private Pattern patternBirthPlace = Pattern.compile("Where was (.*) born?");

  public FuzzyDicSearch() {
    super();

    listOfNovelist = new Searcher_ListOfNovelistFromUS();

    listOfCapitals = new Searcher_ListOfNationalCapitals();

    listOfCEO = new Searcher_ListOfChiefExecutiveOfficers();

    String waAppId = CoreConstants.get().WOLFRAMALPHA_APPID();
    placeOfBirth = new Searcher_BirthPlace(waAppId);

    if (SETUPALL_ON_STARTUP) {
      listOfNovelist.setupMap();
      listOfCapitals.setupMap();
      listOfCEO.setupMap();
      placeOfBirth.setup();
    }
  }

  public String fuzzySearch(String question, boolean needKeyword) {
    String answer = null;

    Matcher matcherNovelist = patternNovelist.matcher(question);
    Matcher matcherCapital = patternCapital.matcher(question);
    Matcher matcherCEO = patternCEO.matcher(question);
    Matcher matcherBirthPlace = patternBirthPlace.matcher(question);

    if (matcherNovelist.find()) {
      if (!listOfNovelist.isSetup()) {
        listOfNovelist.setupMap();
      }
      String name = matcherNovelist.group(1);
      if (name == null) {
        // XXX: error
      } else {
        name = name.trim();
        answer = listOfNovelist.getMap().get(name);
      }
    }
    if (answer == null && matcherCapital.find()) {
      if (!listOfCapitals.isSetup()) {
        listOfCapitals.setupMap();
      }
      String name = matcherCapital.group(1);
      if (name == null) {
        // XXX: error
      } else {
        name = name.trim();
        answer = listOfCapitals.getMap().get(name);
      }
    }
    if (answer == null && matcherCEO.find()) {
      if (!listOfCEO.isSetup()) {
        listOfCEO.setupMap();
      }
      String name = matcherCEO.group(1);
      if (name == null) {
        // XXX: error
      } else {
        name = name.trim();
        answer = listOfCEO.getMap().get(name);
      }
    }
    if (answer == null && matcherBirthPlace.find()) {
      if (!placeOfBirth.isSetup()) {
        placeOfBirth.setup();
      }
      answer = placeOfBirth.search(question);
    }

    return answer;
  }

  public static void main(String[] args) {

    String message = "xxx the author of the book Jurgen, A Comedy of Justice?";
    String message2 = "What is the capital of Saint Vincent and the Grenadines?";
    String message3 = "Who is the CEO of General Electric?";

    String message4 = "Who is the author of the book The Monks of Monk Hall?";
    String message5 = "What is the capital of Laos?";
    String message6 = "Who is the CEO of Sony Computer Entertainment?";
    String message7 = "Who is the CEO of Google?";

    FuzzyDicSearch search = new FuzzyDicSearch();

    String question;
    String answer;

    question = message;
    answer = search.fuzzySearch(question, false);
    System.out.println(question + " => " + answer);

    question = message2;
    answer = search.fuzzySearch(question, false);
    System.out.println(question + " => " + answer);

    question = message3;
    answer = search.fuzzySearch(question, false);
    System.out.println(question + " => " + answer);

    question = message4;
    answer = search.fuzzySearch(question, false);
    System.out.println(question + " => " + answer);

    question = message5;
    answer = search.fuzzySearch(question, false);
    System.out.println(question + " => " + answer);

    question = message6;
    answer = search.fuzzySearch(question, false);
    System.out.println(question + " => " + answer);

    question = message7;
    answer = search.fuzzySearch(question, false);
    System.out.println(question + " => " + answer);

  }
}
