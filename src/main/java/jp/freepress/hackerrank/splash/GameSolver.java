package jp.freepress.hackerrank.splash;

import java.util.Map;

import jp.freepress.hackerrank.core.Solver;
import jp.freepress.hackerrank.util.Num00;

/**
 * 
 * @see GameProblem
 * @see GameAnswer
 */
public class GameSolver implements Solver<GameProblem, GameAnswer> {

  private FuzzyDicSearch search = new FuzzyDicSearch();

  @Override
  public GameAnswer solve(GameProblem problem) {
    JsonGameGame challenge = problem.getChallenge();

    String fact_question;
    String fact_answer;

    fact_question = challenge.getFact_question();
    // fact_question = StringEscapeUtils.unescapeHtml4( fact_question); // dependency...
    fact_question =
        fact_question != null
            ? fact_question.replaceAll("&amp;", "&").replaceAll("\\\"", "\"")
            : null; // quick fix

    fact_answer = search.fuzzySearch(fact_question, false);

    if (fact_answer == null) {
      return new GameAnswer(false, challenge.getCph_number(), -1);
    }

    GameAnswer answer =
        decode(challenge.getCph_number(), fact_answer, challenge.getFact_cph_answer());

    return answer;
  }

  private GameAnswer decode(String text, String firstOrig, String firstDecrypt) {
    NumberCodec codec = new NumberCodec();
    String seed1;
    String seed2;

    seed1 = firstOrig;
    seed2 = firstDecrypt;
    codec.addSeed(seed1, seed2);

    seed1 = "hundred";
    seed2 = codec.findNum00(text, Num00.HUNDRED);
    if (seed2 != null) {
      codec.addSeed(seed1, seed2);
    }

    seed1 = "thousand";
    seed2 = codec.findNum00(text, Num00.THOUSAND);
    if (seed2 != null) {
      codec.addSeed(seed1, seed2);
    }

    for (int k = 0; k < 5; k++) {
      // Wash, rinse, then repeat
      Map<String, String> map =
          codec.extractProbableStringFromIncompletedStr(text, codec.build(text, true));
      for (Map.Entry<String, String> e : map.entrySet()) {
        seed1 = e.getValue();
        seed2 = e.getKey();
        codec.addSeed(seed1, seed2);
      }
    }

    String maskedResult = codec.build(text, true);
    int result = codec.incompletedStrToNumber(maskedResult);
    GameAnswer answer = new GameAnswer(result != -1, maskedResult, result);
    return answer;
  }
}
