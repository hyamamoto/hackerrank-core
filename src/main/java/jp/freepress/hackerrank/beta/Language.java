package jp.freepress.hackerrank.beta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public enum Language {
  C("c", "C"), //
  Cpp("cpp", "C++"), //
  Java("java", "Java"), //
  Csharp("csharp", "C#"), //
  PHP("php", "PHP"), //
  Ruby("ruby", "Ruby"), //
  Python("python", "Python"), //
  Perl("perl", "Perl"), //
  Haskel("haskell", "Haskel"), //
  Clojure("clojure", "Clojure"), //
  Scala("scala", "Scala");

  private String id;
  private String langName;

  private Language(String langId, String langName) {
    this.id = langId;
    this.langName = langName;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLangName() {
    return langName;
  }

  public void setLangName(String langName) {
    this.langName = langName;
  }

  public static Language match( String id) {
    for (Language c: values()) {
      if ( c.getId().equalsIgnoreCase( id) || c.getLangName().equalsIgnoreCase( id)) {
        return c;
      }
    }
    return null;
  }
  
  /**
   * @return A name of the language. A parameter value will be returned if the language with that name is not found.
   */
  public static String toLanguageName( String languageIdOrName) {
    Language lang = match( languageIdOrName);
    return lang == null ? languageIdOrName: lang.langName;
  }

  /**
   * @return A name of the language. A parameter value will be returned if the language with that name is not found.
   */
  public static List<String> toLanguageNames( String languageIdsOrNames) {
    @SuppressWarnings("resource")
    Scanner scanner = new Scanner( languageIdsOrNames);
    scanner.useDelimiter(",");
    List<String> languageNames = new ArrayList<String>();
    while( scanner.hasNext()) {
      String langName = toLanguageName( scanner.next().trim());
      languageNames.add( langName);
    }
    return languageNames;
  }
}