package jp.freepress.hackerrank.scala

import org.apache.http.impl.client.DefaultHttpClient
import jp.freepress.hackerrank.core.HackerRankAPI
import jp.freepress.hackerrank.core.JsonUserStat
import jp.freepress.hackerrank.core.JsonUserStat.Util.toSimpleLineString;

/**
 * @author ${user.name}
 */
object LeaderboardApp {

  def main(args : Array[String]) {
    var api = new HackerRankAPI();

    // Getting a leaderboard
    var leaderboard = api.leaderboard();
    if ( Option(leaderboard).isEmpty) {
      return;
    }

    // Printing all the userstats on the leaderboard
    leaderboard.getBoard().foreach( s => println( toSimpleLineString(s)))
  }

}
