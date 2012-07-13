package jp.freepress.hackerrank.splash;

import java.util.ArrayList;

import jp.freepress.hackerrank.core.Solver;
import jp.freepress.hackerrank.util.Num;
import jp.freepress.hackerrank.util.PoormanCaesar;
import jp.freepress.hackerrank.util.PoormanCaesar.Rotater;

/**
 * <p>
 * GameSolver for Game #1 - #10000.
 * </p>
 * 
 * @author Hiroshi Yamamoto
 * @see PoormanCaesar
 */
public class GameSolver_1_PoormanCaesar implements Solver<GameProblem, GameAnswer> {

  private ArrayList<PoormanCaesar.Rotater> caesarRotaters = new ArrayList<PoormanCaesar.Rotater>();
  {
    for (int i = 0; i < 26; i++) {
      caesarRotaters.add(PoormanCaesar.lowerAscii26Rotater(i));
    }
  }

  @Override
  public GameAnswer solve(GameProblem problem) {
    JsonGameGame challenge = problem.getChallenge();
    String cph_answer = challenge.getCph_number();
    NumberCodec codec = new NumberCodec();

    int maxScore = 0;
    String maxScoreText = null;
    for (Rotater r : caesarRotaters) {
      String a = new String(PoormanCaesar.rotate(r, cph_answer.toCharArray()));
      int score = 0;
      for (Num num : Num.values()) {
        if (a.indexOf(num.asStr()) != -1) {
          score++;
        }
      }
      if (score > maxScore) {
        maxScoreText = a;
      }
    }

    if (maxScoreText == null) {
      return new GameAnswer(false, challenge.getCph_number(), -1);
    }

    int result = codec.incompletedStrToNumber(maxScoreText);
    GameAnswer answer = new GameAnswer(result != -1, maxScoreText, result);
    return answer;
  }

}
