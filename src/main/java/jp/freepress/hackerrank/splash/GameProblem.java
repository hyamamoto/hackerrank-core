package jp.freepress.hackerrank.splash;

import jp.freepress.hackerrank.core.Problem;

/**
 * 
 * @see GameAnswer
 * @see GameSolver
 */
public class GameProblem implements Problem {

  private JsonGameGame challenge;

  public GameProblem() {
    super();
  }

  public GameProblem(JsonGameGame challenge) {
    super();
    this.challenge = challenge;
  }

  public JsonGameGame getChallenge() {
    return challenge;
  }

}
