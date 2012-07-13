package jp.freepress.hackerrank;

import java.util.logging.Logger;

import jp.freepress.hackerrank.core.AbstractMain;
import jp.freepress.hackerrank.core.JsonLeaderBoard;
import jp.freepress.hackerrank.core.JsonUserStat;
import jp.freepress.hackerrank.core.LeaderboardMainArgs;


/**
 * <p>
 * <code>hackerrank.com</code> leaderboard main class.
 * </p>
 * 
 * @author Hiroshi Yamamoto
 */
public class LeaderboardMain extends AbstractMain {

  public static void main(String[] args) {

    LeaderboardMainArgs mainArgs = LeaderboardMainArgs.parseMainArgs(args);
    final int limit = Math.max(1, mainArgs.limit);

    LeaderboardMain main = new LeaderboardMain();

    Logger log = createLogger(LeaderboardMain.class);

    log.info("Leaderboard");
    log.info("===========");
    log.info("Id: " + main.getProcessId());
    log.info("Limit: " + limit);

    // LEADERBOARD
    JsonLeaderBoard leaderboard = main.h.leaderboard(limit);
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

  public LeaderboardMain() {
    super(true);
  }
}
