package jp.freepress.hackerrank;

import java.util.logging.Logger;

import jp.freepress.hackerrank.beta.BetaAPI;
import jp.freepress.hackerrank.beta.JsonLeaderBoard;
import jp.freepress.hackerrank.beta.JsonLeaderBoardList;
import jp.freepress.hackerrank.beta.LeaderBoardCategory;
import jp.freepress.hackerrank.beta.LeaderboardMainArgs;
import jp.freepress.hackerrank.core.AbstractMain;
import jp.freepress.hackerrank.core.CoreConstants;

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
    final LeaderBoardCategory category = mainArgs.category;
    final String categoryValue = mainArgs.categoryValue;

    BetaLeaderboardMain main = new BetaLeaderboardMain();

    Logger log = createLogger(BetaLeaderboardMain.class);

    log.info("Leaderboard");
    log.info("===========");
    log.info("Id: " + main.getProcessId());
    log.info("Offset: " + offset);
    
    BetaAPI betaAPI = new BetaAPI( main.h);
    
    // LOGIN
    log.info("Logging in...");
    if (!main.doLogin( betaAPI, mainArgs.username, mainArgs.password)) {
      log.info("Login failed for { username: \""
          + (mainArgs.username == null ? "" : mainArgs.username)
          + "\"}. use -U option or --help for more help.");
      return;
    }

    // LEADERBOARD
    JsonLeaderBoardList leaderboard = betaAPI.leaderboard( offset, 50, category, categoryValue);
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

  // XXX: there's a duplicated method at BetaSubmissionsMain
  protected boolean doLogin(BetaAPI api, String username, String password) {
    // LOGIN
    username = username != null ? username : CoreConstants.get().USERNAME();
    password = password != null ? password : CoreConstants.get().PASSWORD();
    api.login(username, password);
    if (h.getLastStatusCode() != 200 && h.getLastStatusCode() != 302) {
      l.log("", -1, "LOGIN", "Login Failure", null);
      // l.log("", -1, "SERVICE_UNAVAILABLE", "At login.", h.getLastStatusLine());
      return false;
    } else {
      l.log("", -1, "LOGIN", "Login Success", null);
      return true;
    }
  }  
}
