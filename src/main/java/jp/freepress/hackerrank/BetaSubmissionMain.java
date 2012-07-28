package jp.freepress.hackerrank;

import java.util.logging.Logger;

import jp.freepress.hackerrank.beta.BetaAPI;
import jp.freepress.hackerrank.beta.JsonSubmission;
import jp.freepress.hackerrank.beta.JsonSubmissionGameData;
import jp.freepress.hackerrank.beta.JsonSubmissionHolder;
import jp.freepress.hackerrank.beta.SubmissionMainArgs;
import jp.freepress.hackerrank.core.AbstractMain;
import jp.freepress.hackerrank.core.CoreConstants;


/**
 * <p>
 * <code>hackerrank.com</code> user submission main class.
 * </p>
 * 
 * @author Hiroshi Yamamoto
 * @since 2012/07/26
 */
public class BetaSubmissionMain extends AbstractMain {

  public static void main(String[] args) {

    SubmissionMainArgs mainArgs = SubmissionMainArgs.parseMainArgs(args);

    BetaSubmissionMain main = new BetaSubmissionMain();
    BetaAPI betaAPI = new BetaAPI( main.h);

    Logger log = createLogger(BetaSubmissionMain.class);

    int submissionId = mainArgs.submissionId;

    log.info("Submission");
    log.info("==========");
    log.info("Id: " + main.getProcessId());
    log.info("Submission Id: " + submissionId);

    // LOGIN
    log.info("Logging in...");
    if (!main.doLogin( betaAPI, mainArgs.username, mainArgs.password)) {
      log.info("Login failed for { username: \""
          + (mainArgs.username == null ? "" : mainArgs.username)
          + "\"}. use -U option or --help for more help.");
      return;
    }
    log.info("Login processed.");

    // Submissions
    int gameoffset = mainArgs.gameoffset;
    JsonSubmissionHolder submissionHolder = betaAPI.submission( submissionId, gameoffset); 
    if ( submissionHolder != null) {
      JsonSubmission model = submissionHolder.getModel();
      if ( model != null) {
        StringBuilder buf = new StringBuilder();
        buf.append("Submission(" + submissionId + ")\n");
        buf.append("===============\n");
        buf.append("Score       : ").append(model.getScore()).append("\n");
        buf.append("Win/Lose/Tie: ");
        int game_tie = model.getGame_played() - model.getGame_won() - model.getGame_lost();
        buf.append(model.getGame_won()).append('/').append(model.getGame_lost());
        buf.append('/').append(game_tie).append("\n");
        buf.append("Total       : ").append(model.getGame_played()).append("\n");
        buf.append("Challenge Id: ").append(model.getChallenge_id()).append("\n");
        buf.append("Player Id   : ").append(model.getHacker_id()).append("\n");
        JsonSubmissionGameData[] gamedataArray = model.getGamedata();
        if ( gamedataArray != null) {
          buf.append("\n");
          buf.append("Games.Offset: ").append(gameoffset).append("\n");
          buf.append("Games.Limit : ").append(gamedataArray.length).append("\n");
          buf.append("Games.Total : ").append(model.getGame_played()).append("\n");
          for ( JsonSubmissionGameData gamedata: gamedataArray) {
            buf.append("\n");
            JsonSubmissionGameData.Util.appendGameData(buf, gamedata);
          }
        }
        System.out.println(buf);
      } else {
        System.out.println( "No Such Submission: " + submissionHolder);
      }
    } else {
        System.out.println( "Failed to get Submission data");
    }

    // LOGOUT
    // log.info("Logging out...");
    // main.doLogout();
    // log.info("Logout success. bye.");
  }

  public BetaSubmissionMain() {
    super(true);
  }
  
 protected boolean doLogin(BetaAPI api, String username, String password) {
    // LOGIN
    username = username != null ? username : CoreConstants.get().USERNAME();
    password = password != null ? password : CoreConstants.get().PASSWORD();
    api.login(username, password);
    if (h.getLastStatusCode() != 200 && h.getLastStatusCode() != 302) {
      l.log("", -1, "LOGIN", "Login Failure", null);
      //l.log("", -1, "SERVICE_UNAVAILABLE", "At login.", h.getLastStatusLine());
      return false;
    } else {
      l.log("", -1, "LOGIN", "Login Success", null);
      return true;
    }
  }

}
