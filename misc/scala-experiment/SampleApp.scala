
import jp.freepress.hackerrank.core.HackerRankAPI
import jp.freepress.hackerrank.splash.JsonUserStat
import jp.freepress.hackerrank.splash.JsonUserStat.Util.toSimpleLineString
import jp.freepress.hackerrank.splash.SplashAPI

/**
 * @author ${user.name}
 */
object SampleApp {

  def main(args: Array[String]) {
    val api = new HackerRankAPI();
    val splashAPI = new SplashAPI(api);

    // SIGNUP
    // ======
    // var login = splashAPI.sign_up( "a", "a@abc.com", "pass");
    // println( login)

    // LOGIN
    // =====
    var login = splashAPI.sign_in( "{USERNAME HERE}", "{PASSWORD HERE}");
    println( "\nLogin");
    println(login);
    if (login == null || login.getId() == null) {
      println("Login Failed");
      System.exit(0);
    }

    // SpaceX GAME!
    // ============
    // Query a game #1
    var game = splashAPI.spacexProblem(1);
    println( "\nSpaceX");
    println(game);
    // Answer the game ( Remove these comments if you have enough confidence for your answer.)
    //var gameId = game.getGame().getId();
    //var myAnswer = "{MY_ANSWER}";
    //var answer = splashAPI.spacexAnswer( gameId, myAnswer);
    //println(answer);

    // Candies GAME!
    // =============
    // Query a game #7
    println( "\nCandies");
    var newChallengeGame = splashAPI.candiesProblem(7);
    println(newChallengeGame);
    // Pick a candy
    var challenge = splashAPI.candiesAnswer(1);
    var challengeGame = challenge.getGame();
    println(challengeGame);
    // Pick all candies left, and win
    challenge = splashAPI.candiesAnswer(challenge.getGame().getCurrent());
    println(challenge);

    // USER STATUS
    // ===========
    var userstats = splashAPI.userstats();
    println( "\nUSERSTATUS");
    println( toSimpleLineString(userstats));

    // LEADERBOARD
    // ===========
    var leaderboard = splashAPI.leaderboard();
    println( "\nLEADERBOARD");
    leaderboard.getBoard().foreach( s => println( toSimpleLineString(s)));

    // LOGOUT
    // ======
    splashAPI.sign_out();

  }
}
