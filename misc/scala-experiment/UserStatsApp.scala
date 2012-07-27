
import org.apache.http.impl.client.DefaultHttpClient
import jp.freepress.hackerrank.core.HackerRankAPI
import jp.freepress.hackerrank.splash.JsonUserStat
import jp.freepress.hackerrank.splash.JsonUserStat.Util.toSimpleLineString;
import jp.freepress.hackerrank.splash.JsonLogin
import jp.freepress.hackerrank.splash.SplashAPI

/**
 * @author ${user.name}
 */
object UserStatsApp {

  def main(args : Array[String]) {
    var api = new HackerRankAPI();
    var splashapi = new SplashAPI( api);
    
    var login = splashapi.sign_in( "{USERNAME}", "PASSWORD");

    // Getting a user status
    var stat = splashapi.userstats;
    if ( Option(stat).isEmpty)
      return;

    // Printing a user status
    println( toSimpleLineString( stat))
  }

}
