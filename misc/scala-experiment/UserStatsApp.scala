package jp.freepress.hackerrank.scala

import org.apache.http.impl.client.DefaultHttpClient
import jp.freepress.hackerrank.core.HackerRankAPI
import jp.freepress.hackerrank.core.JsonUserStat
import jp.freepress.hackerrank.core.JsonUserStat.Util.toSimpleLineString;
import jp.freepress.hackerrank.core.JsonLogin

/**
 * @author ${user.name}
 */
object UserStatsApp {

  def main(args : Array[String]) {
    var api = new HackerRankAPI();
    
    var login = api.sign_in( "{USERNAME}", "PASSWORD");

    // Getting a user status
    var stat = api.userstats;
    if ( Option(stat).isEmpty)
      return;

    // Printing a user status
    println( toSimpleLineString( stat))
  }

}
