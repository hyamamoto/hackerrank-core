package jp.freepress.hackerrank.splash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.freepress.hackerrank.core.NeedsRetryException;
import jp.freepress.hackerrank.core.Solver;
import jp.freepress.hackerrank.util.PoormanCaesar;
import jp.freepress.hackerrank.util.PoormanCaesar.Rotater;
import jp.freepress.hackerrank.util.Searcher_ListOfPhobia;
import jp.freepress.hackerrank.util.WordList;

/**
 * <p>
 * GameSolver for Game #10001 - #11100.
 * </p>
 * <p>
 * <b>Sample:</b>
 * 
 * <pre>
 * {
 * "game":
 * {
 *   "n":10001,
 *   "cph_question":"Decipher 'Cwbnbsijbuaiom'.",
 *   "sample_question":"What is the capital of Christmas Island?",
 *   "sample_cph_answer":"Zfscha Zcmb Wipy",
 *   "id":1747308,
 *   "source":[
 *             "aHR0cDovL2VuLndpa2lwZWRpYS5vcmcvd2lraS9MaXN0X29mX25vdmVsaXN0\nc19mcm9tX3RoZV9Vbml0ZWRfU3RhdGVz\n",
 *             "aHR0cDovL2VuLndpa2lwZWRpYS5vcmcvd2lraS9MaXN0X29mX25hdGlvbmFs\nX2NhcGl0YWxz\n",
 *             "aHR0cDovL2VuLndpa2lwZWRpYS5vcmcvd2lraS9MaXN0X29mX2NoaWVmX2V4\nZWN1dGl2ZV9vZmZpY2Vycw==\n"]
 * },
 * "ok":true
 * }
 *  => Flying Fish Cove
 * </pre>
 * <p>
 * 
 * @author Hiroshi Yamamoto
 * @see PoormanCaesar
 * @see FuzzyDicSearch;
 */
public class GameSolver_10001_HybridCaesar implements Solver<GameProblem, GameAnswer> {

  private FuzzyDicSearch search = new FuzzyDicSearch();

  private WordList wordListPhobia = new Searcher_ListOfPhobia();

  private Pattern patternQuestion = Pattern.compile("Decipher \\'(.*)\\'\\.");

  private List<Rotater> poormen = new ArrayList<PoormanCaesar.Rotater>();
  {
    for (int i = 0; i < 26; i++) {
      poormen.add(PoormanCaesar.ascii26Rotater(i));
    }
  }

  @Override
  public GameAnswer solve(GameProblem problem) {
    JsonGameGame challenge = problem.getChallenge();

    String cph_question = challenge.getCph_question();
    Matcher matcherQuestion = patternQuestion.matcher(cph_question);
    if (!matcherQuestion.find()) {
      return new GameAnswer(false, "Question in malformed format : \""
          + challenge.getCph_question() + "", -1);
    }
    String cph_text = matcherQuestion.group(1);
    if (cph_text == null) {
      return new GameAnswer(false, "Question in malformed format : \""
          + challenge.getCph_question() + "", -1);
    }
    cph_text = cph_text.trim();

    // Let's see if it's in my word list
    String decodedTextAsPhobiaName = decodePhobiaName(cph_text);
    if (decodedTextAsPhobiaName != null) {
      return new GameAnswer(true, decodedTextAsPhobiaName, -1);
    }

    // Not I have to find the answer

    String fact_question;
    String fact_answer;
    String cph_answer;

    fact_question = challenge.getSample_question();
    fact_question =
        fact_question != null
            ? fact_question.replaceAll("&amp;", "&").replaceAll("\\\"", "\"")
            : null; // quick fix

    cph_answer = challenge.getSample_cph_answer();

    if ("Who is the CEO of ?".equals(fact_question) && cph_answer.isEmpty()) {
      // Invisible CEO
      String decoded_text = blindPoormenWillfullyDecode(cph_text);
      if (decoded_text != null) {
        return new GameAnswer(true, decoded_text, -1);
      } else {
        return new GameAnswer(false, "Failed to bruteforce-decode : \"" + cph_text + "\"", -1);
      }
    } else {
      fact_answer = search.fuzzySearch(fact_question, false);
      if (fact_answer == null) {
        return new GameAnswer(false, "Sample question couldn't match : \"" + fact_question + "\"",
            -1);
      } else {
        GameAnswer answer = decode(cph_text, fact_answer, cph_answer);
        return answer;
      }
    }

  }

  private GameAnswer decode(String text, String firstOrig, String firstDecrypt) {
    NumberCodec codec = new NumberCodec();
    String seed1;
    String seed2;

    seed1 = firstOrig;
    seed2 = firstDecrypt;
    codec.addSeed(seed1, seed2);

    String maskedResult = codec.build(text, true);

    if (text.length() != maskedResult.length()) {
      return new GameAnswer(false, maskedResult, -1);
    }

    int exactDelta = PoormanCaesar.scanForDeltaAscii26(seed2, seed1, '_');
    String maxScoreText;
    if (exactDelta != -1) {
      maxScoreText = PoormanCaesar.rotateAscii26(text, exactDelta);
    } else {
      DecodeResult decodeResult = poormenDecode(text, maskedResult, false);
      maxScoreText = decodeResult != null ? decodeResult.getDecodedText() : null;
    }
    GameAnswer answer = new GameAnswer(maxScoreText != null, maxScoreText, -1);
    return answer;
  }

  /**
   * Caesar Cipher decoding with multiple masked hints, completes with a probability scoring.
   */
  private DecodeResult poormenDecode(String text, String maskedResult, boolean exactMatch) {
    int maxScore = 0;
    String maxScoreText = null;

    for (Rotater r : poormen) {
      String a = new String(PoormanCaesar.rotate(r, text.toCharArray()));
      int score = 0;
      for (int i = 0; i < maskedResult.length(); i++) {
        char textCh = a.charAt(i);
        char targetCh = maskedResult.charAt(i);
        if (textCh == targetCh) {
          score++;
        } else if (exactMatch && textCh != '_') {
          continue;
        }
      }
      if (score > maxScore) {
        maxScoreText = a;
        maxScore = score;
      }
    }
    if (exactMatch) {
      if (maxScore == 0) {
        return null;
      } else {
        return new DecodeResult(maxScore, maxScoreText, maskedResult);
      }
    } else {
      return new DecodeResult(maxScore, maxScoreText, maskedResult);
    }
  }

  private String blindPoormenWillfullyDecode(String text) {

    // code beyond this line will not be executed if I have a perfect word list.
    // these are code to find and add a new word temporary.

    Map<String, String> map = new HashMap<String, String>();
    // Comment out if we have more hint for special conversion
    // String fact_answer;
    // String cph_answer;
    // Describe more hint here.
    // fact_answer = "Keraunophobia";
    // cph_answer = "Dxktnghiahubt";
    // map.put(fact_answer, cph_answer);
    List<DecodeResult> results = new ArrayList<DecodeResult>();
    for (Map.Entry<String, String> e : map.entrySet()) {
      NumberCodec codec = new NumberCodec();
      String seed1 = e.getKey();
      String seed2 = e.getValue();
      codec.addSeed(seed1, seed2);
      String maskedResult = codec.build(text, true);
      if (maskedResult != null) {
        DecodeResult decodeResult = poormenDecode(text, maskedResult, true);
        if (decodeResult != null) {
          results.add(decodeResult);
        }
      }
    }
    if (!results.isEmpty()) {
      Collections.sort(results);
      // check if the highest score is unique
      Iterator<DecodeResult> iterator = results.iterator();
      DecodeResult first = iterator.next();
      int highestScore = first.getScore();
      // Too few match will lead wrong answer.
      // Precision needs to be very high like 11 out of 13 characters must be matched.
      if (text.length() < 10 || highestScore < text.length() - 2) {
        throw new NeedsRetryException("Need more precision. [ length=" + text.length()
            + ", maxScore=" + highestScore + " , decodedText=" + first.getDecodedText() + "]");
      }
      while (iterator.hasNext()) {
        int score = iterator.next().getScore();
        if (score == highestScore) {
          throw new NeedsRetryException("Duplicated Score: " + score);
        }
      }
      return results.get(0).getDecodedText();
    }
    return null;
  }

  public String decodePhobiaName(String text) {
    for (Rotater r : poormen) {
      String a = new String(PoormanCaesar.rotate(r, text.toCharArray()));
      if (isExactlyPhobiaName(a)) {
        return a;
      }
    }
    return null;
  }

  public boolean isExactlyPhobiaName(String text) {
    if (!wordListPhobia.isSetup()) {
      wordListPhobia.setupList();
    }
    return wordListPhobia.getList().contains(text);
  }

  protected class DecodeResult implements Comparable<DecodeResult> {
    private int score;
    private String decodedText;
    private String encodedText;

    public DecodeResult(int score, String decodedText, String encodedText) {
      super();
      this.score = score;
      this.decodedText = decodedText;
      this.encodedText = encodedText;
    }

    @Override
    public int compareTo(DecodeResult o) {
      return o.score - score;
    }

    public int getScore() {
      return score;
    }

    public void setScore(int score) {
      this.score = score;
    }

    public String getDecodedText() {
      return decodedText;
    }

    public void setDecodedText(String decodedText) {
      this.decodedText = decodedText;
    }

    public String getEncodedText() {
      return encodedText;
    }

    public void setEncodedText(String encodedText) {
      this.encodedText = encodedText;
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("DecodeResult [ ");
      builder.append(score);
      builder.append(", decoded=");
      builder.append(decodedText);
      builder.append(", encoded=");
      builder.append(encodedText);
      builder.append("]");
      return builder.toString();
    }

  }
}

// NOTE:
//
// // 103
// fact_answer = "nine thousand, seven hundred and twenty";
// cph_answer = "gbgx mahnltgw, lxoxg angwkxw tgw mpxgmr";
// map.put( fact_answer, cph_answer);
//
// // 2271
// fact_answer = "seven thousand, four hundred and ninety-nine";
// cph_answer = "tfwfo uipvtboe, gpvs ivoesfe boe ojofuz-ojof";
// map.put( fact_answer, cph_answer);
//
// // 2872
// fact_answer = "four thousand, eight hundred and eighty-six";
// cph_answer = "qzfc eszfdlyo, ptrse sfyocpo lyo ptrsej-dti";
// map.put( fact_answer, cph_answer);
//
// // 3816
// fact_answer = "six thousand, six hundred and thirteen";
// cph_answer = "euj ftagemzp, euj tgzpdqp mzp ftudfqqz";
// map.put( fact_answer, cph_answer);
//
// // 7690
// fact_answer = "three thousand and sixty-one";
// cph_answer = "ocmzz ocjpnviy viy ndsot-jiz";
// map.put( fact_answer, cph_answer);
//
// // 8713
// fact_answer = "one thousand, one hundred and thirty-two";
// cph_answer = "cbs hvcigobr, cbs vibrfsr obr hvwfhm-hkc";
// map.put( fact_answer, cph_answer);
//
// // 8820
// fact_answer = "eight hundred and fifty-five";
// cph_answer = "bfdeq erkaoba xka cfcqv-cfsb";
// map.put( fact_answer, cph_answer);
//
// // 9785
// fact_answer = "six thousand, eight hundred and seventy-one";
// cph_answer = "kap lzgmksfv, wayzl zmfvjwv sfv kwnwflq-gfw";
// map.put( fact_answer, cph_answer);
//
