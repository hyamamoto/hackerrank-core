package jp.freepress.hackerrank;

import java.util.logging.Logger;

import jp.freepress.hackerrank.beta.BetaAPI;
import jp.freepress.hackerrank.beta.JsonLeaderBoard;
import jp.freepress.hackerrank.beta.JsonLeaderBoardList;
import jp.freepress.hackerrank.beta.LeaderboardMainArgs;
import jp.freepress.hackerrank.core.AbstractMain;

/**
 * <p>
 * <code>hackerrank.com</code> leaderboard main class.
 * </p>
 * 
 * @author Hiroshi Yamamoto
 */
public class BetaLeaderboardMain extends AbstractMain {

  public static void main(String[] args) {

    LeaderboardMainArgs mainArgs = LeaderboardMainArgs.parseMainArgs(args);
    final int offset = Math.max(0, mainArgs.offset);

    BetaLeaderboardMain main = new BetaLeaderboardMain();

    Logger log = createLogger(BetaLeaderboardMain.class);

    log.info("Leaderboard");
    log.info("===========");
    log.info("Id: " + main.getProcessId());
    log.info("Offset: " + offset);
    
    BetaAPI betaAPI = new BetaAPI( main.h);

    // LEADERBOARD
    JsonLeaderBoardList leaderboard = betaAPI.leaderboard( offset, 50);
    if (leaderboard == null) {
      log.info("Failed to retrieve a leaderboard: " + main.h.getLastStatusLine());
      return;
    }
    plotLeaderBoards(leaderboard.getModels());

//    JsonLeaderBoardList leaderboard2 = betaAPI.leaderboard( offset + 13, 13);
//    if ( leaderboard2 != null) {
//      plotLeaderBoards(leaderboard2.getModels());
//    }
  }

  private static void plotLeaderBoards( JsonLeaderBoard[] boards) {
    if (boards != null) {
      for (JsonLeaderBoard stat : boards) {
        String statStr = JsonLeaderBoard.Util.toSimpleLineString(stat);
        System.out.println(statStr);
      }
    }
  }

  public BetaLeaderboardMain() {
    super(true);
  }
}
