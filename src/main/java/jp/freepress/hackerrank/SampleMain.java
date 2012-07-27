package jp.freepress.hackerrank;

import jp.freepress.hackerrank.core.HackerRankAPI;
import jp.freepress.hackerrank.splash.JsonChallenge;
import jp.freepress.hackerrank.splash.JsonChallengeGame;
import jp.freepress.hackerrank.splash.JsonGame;
import jp.freepress.hackerrank.splash.JsonLeaderBoard;
import jp.freepress.hackerrank.splash.JsonLogin;
import jp.freepress.hackerrank.splash.JsonUserStat;
import jp.freepress.hackerrank.splash.SplashAPI;

/**
 * Sample application for <tt>hackerrank.com</tt>
 * 
 * @see HackerRankAPI
 * @see SplashAPI
 * @author {YOUR NAME}
 */
public class SampleMain {

  public static void main(String[] argv) {

    HackerRankAPI coreAPI = new HackerRankAPI();
    SplashAPI splashAPI = new SplashAPI(coreAPI);

    // SIGNUP
    // ======
    // JsonLogin newUser = coreAPI.sign_up("a", "a@abc.com", "pass");
    // System.out.println(newUser);


    // LOGIN
    // =====
    JsonLogin login = splashAPI.sign_in("{USERNAME HERE}", "{PASSWORD HERE}");
    System.out.println(login);
    if (login == null || login.getId() == null) {
      System.out.println("Login Failed");
      System.exit(0);
    }


    // SpaceX GAME!
    // ============
    // Query a game #1
    JsonGame game = splashAPI.spacexProblem(1);
    System.out.println(game);
    // Answer the game ( Remove these comments if you have enough confidence for your answer.)
    // String gameId = game.getGame().getId();
    // String myAnswer = "{MY_ANSWER}";
    // JsonGameAnswer answer = splashAPI.spacexAnswer(gameId, myAnswer);
    // System.out.println(answer);


    // Candies GAME!
    // =============
    // Query a game #7
    JsonChallengeGame newChallengeGame = splashAPI.candiesProblem(7);
    System.out.println(newChallengeGame);
    // Pick a candy
    JsonChallenge challenge = splashAPI.candiesAnswer(1);
    JsonChallengeGame challengeGame = challenge.getGame();
    System.out.println(challengeGame);
    // Pick all candies left, and win
    challenge = splashAPI.candiesAnswer(challenge.getGame().getCurrent());
    System.out.println(challenge);


    // USER STATUS
    // ===========
    JsonUserStat userstats = splashAPI.userstats();
    System.out.println(userstats);


    // LEADERBOARD
    // ===========
    JsonLeaderBoard leaderboard = splashAPI.leaderboard();
    System.out.println(leaderboard);

    // LOGOUT
    // ======
    splashAPI.sign_out();

  }

}
