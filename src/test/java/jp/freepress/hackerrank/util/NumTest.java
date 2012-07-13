package jp.freepress.hackerrank.util;

import static jp.freepress.hackerrank.util.Num.findBestMatch;

import java.util.Collection;

import junit.framework.TestCase;

// NOTE: this really isn't a test. they are just snippets
public class NumTest extends TestCase {

  public void testFindBestMatch() {
    
    // GUESSING FROM ERRORNOUS TEXT
    printFindBestMatch("zack", null);
    printFindBestMatch("UNI", null);
    printFindBestMatch("uno", null);
    printFindBestMatch("onu", null);
    printFindBestMatch("one", null);
    printFindBestMatch("tno", null);
    printFindBestMatch("sig", null);
    printFindBestMatch("gine", null);
    printFindBestMatch("gibe", null);
    printFindBestMatch("give", null);
    printFindBestMatch("fine", null);
    printFindBestMatch("esghn", null);
    printFindBestMatch("esgghn", null);
    printFindBestMatch("esggtn", null);
    printFindBestMatch("zighsx-zighs", null);
    printFindBestMatch("eighty-eight", null);
    printFindBestMatch("yiyty-sin", null);
    printFindBestMatch("yiktu-yik", null);
    printFindBestMatch("fiftu-yik", null);
    printFindBestMatch("fiftu-yik", null);
    
    // PARTIAL MATCH
    printFindBestMatch("zero", null);
    printFindBestMatch("_h___", null);
    printFindBestMatch("e____", null);
    printFindBestMatch("e_____", null);
    printFindBestMatch("e__v__", null);
    printFindBestMatch("e___t_", null);
    printFindBestMatch("thirty-four", null);
    printFindBestMatch("_i_t_-_i_", null);
    printFindBestMatch("__x__-_i_", null);
    printFindBestMatch("__f__-__e", null);
    printFindBestMatch("__f__-n___", null);
    printFindBestMatch("_i_ty-_i_e", null);
    printFindBestMatch("nin___-nin_", null);
    printFindBestMatch("______d", null);

    // LENGTH MATCH 00
    printFindBestMatch00("_______", null);
    printFindBestMatch00("________", null);
    printFindBestMatch00("hundred", null);
    printFindBestMatch00("thousand", null);
  }

  private static Num printFindBestMatch(String candi, Collection<Character> resolvedChars) {
    Num match = findBestMatch(candi, null, resolvedChars);
    System.out.println(candi + " => " + match);
    return match;
  }


  private static Num00 printFindBestMatch00(String candi, Collection<Character> resolvedChars) {
    Num00 match = Num00.findBestMatch(candi, true, resolvedChars);
    System.out.println(candi + " => " + match);
    return match;
  }

}
