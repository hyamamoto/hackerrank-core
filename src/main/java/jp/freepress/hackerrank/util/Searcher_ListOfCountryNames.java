package jp.freepress.hackerrank.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * List of country names from a word list, "ListOfCountryNames.txt".<br/>
 * 
 * @author Hiroshi Yamamoto
 */
public class Searcher_ListOfCountryNames implements WordList {

  private List<String> wordList;

  @Override
  public boolean isSetup() {
    return wordList != null;
  }

  @Override
  public void setupList() {
    wordList = new ArrayList<String>();
    InputStream stream = Searcher_ListOfCountryNames.class.getResourceAsStream("ListOfCountryNames.txt");
    BufferedReader br = new BufferedReader(new InputStreamReader(stream));
    String strLine;
    int wordCount = 0;
    try {
      while ((strLine = br.readLine()) != null) {
        wordList.add(strLine);
        wordCount++;
        // System.out.println (strLine);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("ListOfCountryNames Loaded: " + wordCount + " entries");
  }

  @Override
  public List<String> getList() {
    return wordList;
  }

  public static void main(String[] args) {

    Searcher_ListOfCountryNames searcher = new Searcher_ListOfCountryNames();
    searcher.setupList();
    List<String> wordlist = searcher.getList();
    for (String word : wordlist) {
      System.out.println(word);
    }
    System.out.println(wordlist.size());

  }
}
