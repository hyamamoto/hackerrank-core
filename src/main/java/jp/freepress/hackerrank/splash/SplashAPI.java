package jp.freepress.hackerrank.splash;

import jp.freepress.hackerrank.core.HackerRankAPI;

/**
 * <p>
 * HackerRank API Wrapper. <br/>
 * This covers the two games on HackerRank pre-beta challenges.
 * <dl>
 * <dt>SpaceX</dt>
 * <dd>
 * <ul>
 * <li>spacexProblem()</li>
 * <li>spacexAnswer()</li>
 * </ul>
 * </dd>
 * <dt>Candies</dt>
 * <dd>
 * <ul>
 * <li>candiesProblem()</li>
 * <li>candiesAnswer()</li>
 * </ul>
 * </dd>
 * </dl>
 * </p>
 * All the methods are using {@link HackerRankAPI}'s lower level methods to communicate with the
 * server.
 * 
 * @author Hiroshi Yamamoto
 * @see HackerRankAPI
 */
public class SplashAPI {

  private final HackerRankAPI h;

  public SplashAPI(HackerRankAPI api) {
    super();
    this.h = api;
  }

  public JsonGame spacexProblem(int problemNo) {
    JsonGame game = h.game(JsonGameImpl.class, problemNo);
    return game;
  }

  public JsonGameAnswer spacexAnswer(String gameId, String answerText) {
    JsonGameAnswer answer = h.gameAnswer(JsonGameAnswerImpl.class, gameId, answerText);
    return answer;
  }

  public JsonChallengeGame candiesProblem(int amountOfCandies) {
    JsonChallengeGame game = h.challenge(JsonChallengeGameImpl.class, amountOfCandies);
    return game;
  }

  public JsonChallenge candiesAnswer(int candiesToMove) {
    JsonChallenge challenge = h.challengeAnswer(JsonChallengeImpl.class, candiesToMove);
    return challenge;
  }

}
