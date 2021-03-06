package jp.freepress.hackerrank.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * List of obscurely long word list ( a lot of -phobia) from a word list, "ListOfPhobia.txt".<br/>
 * 
 * @author Hiroshi Yamamoto
 */
public class Searcher_ListOfPhobia implements WordList {

  private List<String> phobiaList;

  @Override
  public boolean isSetup() {
    return phobiaList != null;
  }

  @Override
  public void setupList() {
    phobiaList = new ArrayList<String>();
    InputStream stream = Searcher_ListOfPhobia.class.getResourceAsStream("ListOfPhobia.txt");
    BufferedReader br = new BufferedReader(new InputStreamReader(stream));
    String strLine;
    int wordCount = 0;
    try {
      while ((strLine = br.readLine()) != null) {
        phobiaList.add(strLine);
        wordCount++;
        // System.out.println (strLine);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("ListOfPhobia Loaded: " + wordCount + " entries");
  }

  @Override
  public List<String> getList() {
    return phobiaList;
  }

  public static void main(String[] args) {

    Searcher_ListOfPhobia searcher = new Searcher_ListOfPhobia();
    searcher.setupList();
    List<String> wordlist = searcher.getList();
    for (String word : wordlist) {
      System.out.println(word);
    }
    System.out.println(wordlist.size());

  }
}
