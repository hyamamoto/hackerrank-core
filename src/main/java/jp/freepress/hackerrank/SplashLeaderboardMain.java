package jp.freepress.hackerrank;

import java.util.logging.Logger;

import jp.freepress.hackerrank.core.LeaderboardMainArgs;
import jp.freepress.hackerrank.splash.AbstractSplashMain;
import jp.freepress.hackerrank.splash.JsonLeaderBoard;
import jp.freepress.hackerrank.splash.JsonUserStat;


/**
 * <p>
 * <code>hackerrank.com</code> leaderboard main class.
 * </p>
 * 
 * @author Hiroshi Yamamoto
 */
public class SplashLeaderboardMain extends AbstractSplashMain {

  public static void main(String[] args) {

    LeaderboardMainArgs mainArgs = LeaderboardMainArgs.parseMainArgs(args);
    final int limit = Math.max(1, mainArgs.limit);

    SplashLeaderboardMain main = new SplashLeaderboardMain();

    Logger log = createLogger(SplashLeaderboardMain.class);

    log.info("Leaderboard");
    log.info("===========");
    log.info("Id: " + main.getProcessId());
    log.info("Limit: " + limit);

    // LEADERBOARD
    JsonLeaderBoard leaderboard = main.splash.leaderboard(limit);
    if (leaderboard == null) {
      log.info("Failed to retrieve a leaderboard: " + main.h.getLastStatusLine());
      return;
    }
    JsonUserStat[] board = leaderboard.getBoard();
    if (board != null) {
      for (JsonUserStat stat : leaderboard.getBoard()) {
        String statStr = JsonUserStat.Util.toSimpleLineString(stat);
        System.out.println(statStr);
      }
    }

  }

  public SplashLeaderboardMain() {
    super(true);
  }
}
