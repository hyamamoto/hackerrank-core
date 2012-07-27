
import org.apache.http.impl.client.DefaultHttpClient
import jp.freepress.hackerrank.core.HackerRankAPI
import jp.freepress.hackerrank.core.JsonUserStat
import jp.freepress.hackerrank.core.JsonUserStat.Util.toSimpleLineString;
import jp.freepress.hackerrank.splash.SplashAPI

/**
 * @author ${user.name}
 */
object LeaderboardApp {

  def main(args : Array[String]) {
    var api = new HackerRankAPI();
    var splashapi = new SplashAPI( api);

    // Getting a leaderboard
    var leaderboard = splashapi.leaderboard();
    if ( Option(leaderboard).isEmpty) {
      return;
    }

    // Printing all the userstats on the leaderboard
    leaderboard.getBoard().foreach( s => println( toSimpleLineString(s)))
  }

}
